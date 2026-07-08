package org.example;

public class LotteryWinner {
    public String lotteryAd(String participantName, String winnerName) {
        if (winnerName.equalsIgnoreCase(participantName)) {
            return "Поздравляем! Вы выиграли!";
        } else return "Вы не выиграли";
    }
}
