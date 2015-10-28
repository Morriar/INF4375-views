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
package inf4375.server.views.html;

import inf4375.server.HTMLView;
import javax.json.JsonObject;

/**
 * A view that displays an album in HTML format.
 */
public class HTMLAlbumDetails extends HTMLView {

    // The album to display in a JSON format.
    JsonObject album;

    public HTMLAlbumDetails(String viewTitle, JsonObject album) {
        super(viewTitle);
        this.album = album;
    }

    @Override
    public String renderBody() {
        StringBuilder builder = new StringBuilder();
        builder.append("<dl>");
        builder.append(" <dt>Title</dt>");
        builder.append(" <dd>");
        builder.append(album.getString("title"));
        builder.append(" </dd>");
        builder.append(" <dt>Artist</dt>");
        builder.append(" <dd>");
        builder.append(album.getString("artist"));
        builder.append(" </dd>");
        builder.append(" <dt>Price</dt>");
        builder.append(" <dd>$ ");
        builder.append(album.getJsonNumber("price").doubleValue());
        builder.append(" </dd>");
        builder.append(" <dt>Status</dt>");
        builder.append(" <dd> ");
        Boolean instock = album.getBoolean("instock");
        if (instock) {
            builder.append("IN STOCK!!!");
        } else {
            builder.append("out of stock...");
        }
        builder.append(" </dd>");
        builder.append("</dl>");
        return builder.toString();
    }
}
