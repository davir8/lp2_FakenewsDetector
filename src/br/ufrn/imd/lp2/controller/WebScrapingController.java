package br.ufrn.imd.lp2.controller;

import br.ufrn.imd.lp2.model.Quote;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebScrapingController {

	public Quote toCollectData(String url) {
		Quote quote = new Quote();
		
		String created_at = "";
		String content = "";
		try {
			Document doc = Jsoup.connect(url).get();
			
			// Get the created date or collect date
			Elements datas =  doc.getElementsByAttribute("datetime");
			
			if (datas.size() > 0) {
				created_at = datas.first().attr("datetime").toString();
			} else {
				Date date = new Date();
				created_at = date.toString();
			}
			quote.setDate(created_at);
			
			// Get the paragraphs 
			Elements paragraphs = doc.select("p");
			for (Element paragraph : paragraphs) {
				if (paragraph.hasText() && paragraph.text().length() > 50) {
					content = content.concat(paragraph.text()+" ");
				}
			}
			quote.setContent(content);
			
			quote.setUrl(url);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return quote;
	}

	public double measureAccuracy(String quote) {
		return 0;
	}

}
