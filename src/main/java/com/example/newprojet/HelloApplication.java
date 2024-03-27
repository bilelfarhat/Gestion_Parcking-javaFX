package com.example.newprojet;


        import javafx.application.Application;
        import javafx.application.Platform;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import javafx.stage.StageStyle;

        import java.io.IOException;

        import static javafx.stage.Stage.*;

public class HelloApplication extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene =new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);



        Stage splash = new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("splashscreen.fxml"));
        Parent root1=loader.load();
        Scene s=new Scene(root1);
        splash.initStyle(StageStyle.UNDECORATED);
        splash.setScene(s);
        splash.show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        splash.hide();
                        stage.show();
                    }
                });
            }

        }).start();

    }
}