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
package inf4375.server;

/**
 * A specific view used to produce HTML.
 */
public class HTMLView implements View {

    public String viewTitle;

    public HTMLView(String viewTitle) {
        this.viewTitle = viewTitle;
    }

    // Render the HTML fulle page.
    //
    // This method calls `renderBody()` used to render the core of the page.
    @Override
    public String render() {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>");
        builder.append("<html>");
        builder.append(" <head>");
        builder.append("  <h1>");
        builder.append(viewTitle);
        builder.append("  </h1>");
        builder.append(" </head>");
        builder.append(" <body>");
        builder.append(renderBody());
        builder.append(" </body>");
        builder.append("</html>");
        return builder.toString();
    }

    // Render the body of the page.
    //
    // Surrounding elements like html header and footer are rendered by `render()`.
    public String renderBody() {
        return "";
    }
}
