import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;

public class Launcher extends Application{

    @Override
    public void start(Stage stage){
        //setup dimensions
        final int screenWidth = 480;
        final int screenHeight = 640;

        VBox options = new VBox(10);

        Text logoTx = new Text("Card Game Suite");
        logoTx.setFont(Font.font(64));
        logoTx.setFill(Color.LIGHTPINK);

        //buttons
        Button emptyBt1 = new Button("Empty 1");
        Button emptyBt2 = new Button("Empty 2");
        Button blackjackBt = new Button("Blackjack");

        //make buttons do things
        blackjackBt.setOnAction(e -> {
            BlackJack game = new BlackJack();
            game.start(new Stage());
        });

        emptyBt1.setOnAction(e -> {

        });

        emptyBt2.setOnAction(e -> {

        });

        blackjackBt.setOnMouseEntered(e -> blackjackBt.setStyle(""));
        blackjackBt.setOnMouseExited(e -> blackjackBt.setStyle(""));

        //make button pretty
        blackjackBt.setMinWidth(screenWidth*0.8);
        emptyBt1.setMinWidth(screenWidth*0.8);
        emptyBt2.setMinWidth(screenWidth*0.8);

        blackjackBt.setMinHeight(64);
        emptyBt1.setMinHeight(64);
        emptyBt2.setMinHeight(64);

        blackjackBt.setFont(Font.font(32));
        emptyBt1.setFont(Font.font(32));
        emptyBt2.setFont(Font.font(32));

        blackjackBt.setTextFill(Color.BEIGE);
        emptyBt1.setTextFill(Color.BEIGE);
        emptyBt2.setTextFill(Color.BEIGE);

        options.getChildren().addAll(logoTx,emptyBt1,emptyBt2,blackjackBt);
        options.setAlignment(Pos.CENTER);

        BackgroundFill btbackground = new BackgroundFill(Paint.valueOf("brown"),CornerRadii.EMPTY, Insets.EMPTY);

        Image greenwool = new Image("/greenwool.png");
        BackgroundImage background = new BackgroundImage(greenwool, REPEAT, REPEAT, DEFAULT, BackgroundSize.DEFAULT);


        Scene scene = new Scene(options, screenWidth, screenHeight);

        emptyBt1.setBackground(new Background(btbackground));
        emptyBt2.setBackground(new Background(btbackground));
        blackjackBt.setBackground(new Background(btbackground));
        options.setBackground(new Background(background));

        stage.setTitle("Lanucher");
        stage.setScene(scene);
        stage.show();
    }
}
