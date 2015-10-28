/*
 * Copyright 2015 Alexandre Terrasa <alexandre@moz-code.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package inf4375.server.controllers;

import inf4375.server.Request;
import inf4375.server.Router;
import inf4375.server.UriMatchController;
import inf4375.server.views.html.HTMLAlbumDetails;
import inf4375.server.views.html.HTMLAlbumsTable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * A Controller that displays the catalog in JSON format.
 */
public class AlbumsController extends UriMatchController {

    // Json array used to store the albums list.
    JsonArray catalog;

    public AlbumsController(JsonArray catalog) {
        this.uriMatch = "^/albums/?.*";
        this.catalog = catalog;
    }

    @Override
    public void action(Router router, Request request) {
        String uri = request.uri;
        if (uri.matches("^/albums/?$")) {
            actionListAlbums(router);
            return;
        } else if (uri.matches("^/albums/(\\d)$")) {
            Pattern pattern = Pattern.compile("(\\d)");
            Matcher matcher = pattern.matcher(uri);
            if (matcher.find()) {
                actionShowAlbum(router, matcher.group(1));
                return;
            }
        }
        router.sendError(400, "Bad request");
    }

    // Display in stock albums as a JsonArray.
    private void actionListAlbums(Router router) {
        ArrayList<JsonObject> albums = new ArrayList<>();
        for (JsonObject album : catalog.getValuesAs(JsonObject.class)) {
            if (album.getBoolean("instock")) {
                albums.add(album);
            }
        }
        HTMLAlbumsTable view = new HTMLAlbumsTable("Albums list", albums);
        router.sendResponse(200, "OK", view);
    }

    // Display the album with `id` as a JsonObject.
    private void actionShowAlbum(Router router, String id) {
        for (JsonObject album : catalog.getValuesAs(JsonObject.class)) {
            if (album.getString("id").equals(id)) {
                HTMLAlbumDetails view = new HTMLAlbumDetails("Album details", album);
                router.sendResponse(200, "OK", view);
                return;
            }
        }
        router.sendError(404, "Not found");
    }
}
