package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private char nowSym = 'x';

    private char gameField[][] = new char[3][3];

    private boolean isGame = true;

    private Winner winner = Winner.NONE;

    public enum Winner {
        X,
        O,
        DRAW,
        NONE
    }

    @FXML
    void btnClick(ActionEvent event) {
        Button btn = (Button) event.getSource();

        if (!isGame || btn.getText() != "") return;

        int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);

        gameField[rowIndex][columnIndex] = nowSym;

        btn.setText(String.valueOf(nowSym));

        // Проверка строк
        for (int i = 0; i < 3; i++) {
            if (gameField[i][0] == gameField[i][1] && gameField[i][0] == gameField[i][2] && (gameField[i][0] == 'x' || gameField[i][0] == 'o')) {
                isGame = false;
                winner = gameField[i][0] == 'x' ? Winner.X : Winner.O;
            }
        }

        // Проверка столбцов
        for (int i = 0; i < 3; i++) {
            if (gameField[0][i] == gameField[1][i] && gameField[0][i] == gameField[2][i] && (gameField[0][i] == 'x' || gameField[0][i] == 'o')) {
                isGame = false;
                winner = gameField[0][i] == 'x' ? Winner.X : Winner.O;
            }
        }

        // Проверка главной диагонали
        if (gameField[0][0] == gameField[1][1] && gameField[0][0] == gameField[2][2] && (gameField[0][0] == 'x' || gameField[0][0] == 'o')) {
            isGame = false;
            winner = gameField[0][0] == 'x' ? Winner.X : Winner.O;
        }

        // Проверка побочной диагонали
        if (gameField[0][2] == gameField[1][1] && gameField[0][2] == gameField[2][0] && (gameField[0][2] == 'x' || gameField[0][2] == 'o')) {
            isGame = false;
            winner = gameField[0][2] == 'x' ? Winner.X : Winner.O;
        }

        // Проверка на ничью
        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j] == '\0') {
                    isDraw = false;
                    break;
                }
            }
            if (!isDraw) break;
        }
        if (isDraw) {
            isGame = false;
            winner = Winner.DRAW;
        }

        if (winner != Winner.NONE){
            if(winner == Winner.DRAW){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "У нас ничья :)", ButtonType.OK);
                alert.showAndWait();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "У нас победитель <" + winner + ">", ButtonType.OK);
            alert.showAndWait();
        }

        nowSym = nowSym == 'x' ? 'o' : 'x';
    }

    @FXML
    void initialize() {

    }

}
