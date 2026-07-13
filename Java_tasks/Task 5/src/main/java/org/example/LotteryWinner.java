package org.example;

public class LotteryWinner {
    public String lotteryAd(String participantName, String winnerName) {
        if (winnerName.equalsIgnoreCase(participantName)) {
            return "Поздравляем! Вы выиграли!";
        } return "Вы не выиграли";
    }
}
