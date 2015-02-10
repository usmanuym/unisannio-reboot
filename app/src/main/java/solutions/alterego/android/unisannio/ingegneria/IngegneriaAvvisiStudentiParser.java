package solutions.alterego.android.unisannio.ingegneria;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import solutions.alterego.android.unisannio.IParser;

public class IngegneriaAvvisiStudentiParser implements IParser {

    @Override
    public List parse(Document document) {
        Elements elements = document.select("#maincontent-block > #item");

        List<IngegneriaDidatticaItem> list = new ArrayList<>();

        for (Element element : elements) {
            Element header = element.select("#header > h2 > a").first();
            String title = header.text();
            String url = header.attr("href");

            Elements paragraphs = element.select("p");
            String author = "";
            for (Element paragraph : paragraphs) {
                if (paragraph.text().contains("Autore")) {
                    author = paragraph.text().replace("Autore: ", "");
                }
            }
            String body = element.select(".avvtext").first().text();

            String date = element.select("#publishinfo").first().text().replace("Pubblicato il ", "");

            list.add(new IngegneriaDidatticaItem(title, url, author, body, date));
        }
        return list;
    }
}
