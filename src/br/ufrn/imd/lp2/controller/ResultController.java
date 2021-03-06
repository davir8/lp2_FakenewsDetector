package br.ufrn.imd.lp2.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import br.ufrn.imd.lp2.model.AnalysisResult;
import br.ufrn.imd.lp2.view.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultController {

	@FXML
	private Button goBack;

	@FXML
	private Text baloonText;

	@FXML
	private Text fakenews;

	@FXML
	private Text content;

	@FXML
	private Text fakenewsLabel;

	@FXML
	private ImageView character;


	@FXML
	/**
	 * Quando ativado, redireciona o stage ao cen�rio HOME.*/
	void handleSubmit(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/ufrn/imd/lp2/view/Home.fxml"));
		loader.load();
				
		Parent p = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		
        goBack.getScene().getWindow().hide();

        stage.show();
	}

	/**
     * Configura os dados que exibem ao usu�rio a informa��o de resultado
     *
     * @param isFakenews true caso a resposta seja falsa
     * @param percentage � a taxa de certeza que o texto � fakenews
     * @param cnt � o texto da mensagem analisada
     * @param fk � a fakenews encontrada que � compat�vel com o conte�do
     */
	public void setData(Boolean isFakenews, Double percentage, String cnt, String fk) throws FileNotFoundException {
		String message;
		Image image;
		if (isFakenews) {
			String resultado = String.format("%.1f", percentage*100);
			message = "Oh não! Isso é mentira do caixão. Sua notícia é " + resultado + "% falsa";
			fakenewsLabel.setText("Fakenews Existente");
			fakenews.setText(fk);
			image = new Image(new FileInputStream("assets/akinator2.png"));
		} else {
			message = "Aeeee! Não temos registros de sua notícia como falsa";
			fakenewsLabel.setText("");
			image = new Image(new FileInputStream("assets/akinator3.png"));
		}

		baloonText.setText(message);
		character.setImage(image);
		content.setText(cnt);

	}
}
