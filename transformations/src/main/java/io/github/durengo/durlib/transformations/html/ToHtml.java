package io.github.durengo.durlib.transformations.html;

import java.io.FileWriter;
import java.io.IOException;

public class ToHtml {
    protected String FileName = "index.html";
    protected String FinalIndex = "";
    protected String UrlAssetIndex = "";
    protected static final String HtmlDocType = "<!doctype html>\n";
    protected static final String HtmlOpeningTag = "<html lang=\"en\">\n";
    protected static final String HtmlHeadOpeningTag = "  <head>\n";
    protected String HtmlTitle;
    protected static final String HtmlHeadDefault =
            "    <meta charset=\"utf-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ\" crossorigin=\"anonymous\">\n" +
            "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css\">\n" +
            "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe\" crossorigin=\"anonymous\"></script>\n";
    protected static final String HtmlHeadClosingTag = "  </head>";
    protected static final String HtmlBodyOpeningTag = "  <body>";
    protected static final String HtmlBodyDefault = "\n";
    protected static final String HtmlCustomCssOpeningTag = "<style>\n";
    protected static final String HtmlCustomCss = "<!-- CUSTOM CSS GOES HERE -->\n";
    protected static final String HtmlCustomCssClosingTag = "</style>\n";
    protected String HtmlCustom = "";
    protected static final String HtmlCustomJavascriptOpeningTag = "<script>\n";
    protected static final String HtmlCustomJavascript = "<!-- CUSTOM JAVASCRIPT GOES HERE -->\n";
    protected static final String HtmlCustomJavascriptClosingTag = "</script>\n";
    protected static final String HtmlBodyClosingTag = "  </body>\n";
    protected static final String HtmlClosingTag = "</html>\n";

    protected FileWriter out;

    public ToHtml() {}

    public String getHtmlTitle() {
        return HtmlTitle;
    }

    public void setHtmlTitle(String htmlTitle) {
        HtmlTitle = "    <title>" + htmlTitle + "</title>\n";
    }

    public void addToBody(String text)
    {
        HtmlCustom += text;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getUrlAssetIndex() {
        return UrlAssetIndex;
    }

    public void setUrlAssetIndex(String urlAssetIndex) {
        UrlAssetIndex = urlAssetIndex;
    }

    protected void Begin() throws IOException {
        out = new FileWriter(FileName);
    }
    protected void End() throws IOException {
        out.close();
    }

}
