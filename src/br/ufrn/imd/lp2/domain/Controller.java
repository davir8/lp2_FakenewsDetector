package br.ufrn.imd.lp2.domain;

import java.util.ArrayList;

public class Controller {
	private final int MIN_CHARACTERS = 3;
	private final double MIN_ACCURACY = 0.6;
	
	private ArrayList <Quote> quotesSet;
	Controller(ArrayList <String> dataset)
	{
		//se dataset estiver vazio, throw exception
		WebScrapper ws = new WebScrapper();
		
		for(String quote : dataset)
		{
			ArrayList <Exception> errs = null;		// Caso a quote n�o seja Fakenews, ser� ignorado
			
			String standQuote = standardizeQuote(quote);	//adicionar throw exception de erro na string caso vazia
			
			double quoteAccuracy = ws.measureAccuracy(standQuote);		//rodar o web scraping em cima dessa standQuote
			
			if(quoteAccuracy < this.MIN_ACCURACY) 
			{
				Fakenews fn = new Fakenews();
				fn.setQuote(quote);
				//fn.setHash(hash);
				fn.setErrs(errs);
			}else 
			{
				News n = new News();
				n.setQuote(quote);
				//n.setHash(hash);
				n.setNewsAccuracy(quoteAccuracy);
				//n.setSource();
				
			}
			// webscraping vai retornar a accuracy e as fontes
			// o webscraping vai lan�ar excecoes 
			
			
		}
	}
	
	/**
	 *	Fun��o respons�vel por padronizar a string recebida 
	 * @param quote cont�m o texto
	 * @return string no padr�o lowercase, com words maiores que MIN_CHARACTERS e em ordem alfab�tica
	 **/
	public String standardizeQuote(String quote) 
	{
		// lowercase
		// retirar palavras com menos de MIN_CHARACTERS char
		// por em ordem alfab�tica
		return quote;
	}
}