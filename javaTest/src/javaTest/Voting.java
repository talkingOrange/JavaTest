package javaTest;

import java.util.Scanner;

public class Voting {
    public static int[] votes = new int[21];
    public static int[][] ballots = new int[1000][21];
    public static char[][] candidates = new char[21][81];
    public static int candidateCount, ballotCount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        candidateCount = scanner.nextInt();

        if (candidateCount == 0) {
            System.exit(1);
        }

        scanner.nextLine();

        readCandidates(scanner);

        ballotCount = 0;

        while (scanner.hasNextLine()) {
            String[] nums = scanner.nextLine().split(" ");

            if (nums.length < candidateCount) {
                break;
            }

            readBallots(nums);
            ballotCount++;
        }

        calculateResults();
    }

    public static void readCandidates(Scanner scanner) {
        for (int i = 0; i < candidateCount; i++) {
            candidates[i] = scanner.nextLine().toCharArray();
        }
    }

    public static void readBallots(String[] nums) {
        for (int i = 0; i < candidateCount; i++) {
            ballots[ballotCount][i] = Integer.parseInt(nums[i]) - 1;
        }
    }

    public static void calculateResults() {
        int minVotes;

        while (true) {
            resetVotes();

            int j;
            for (int i = 0; i < ballotCount; i++) {
                for (j = 0; j < candidateCount; j++) {
                    if (candidates[ballots[i][j]][0] != '\0') break;
                }
                votes[ballots[i][j]]++;
            }

            minVotes = ballotCount;

            for (int i = 0; i < candidateCount; i++) {
                if (votes[i] > ballotCount / 2) {
                    System.out.println(String.valueOf(candidates[i]));
                    System.exit(0);
                }
                if (candidates[i][0] != '\0' && votes[i] < minVotes) {
                    minVotes = votes[i];
                }
            }

            int i;
            for (i = 0; i < candidateCount; i++) {
                if (candidates[i][0] != '\0' && votes[i] != minVotes) {
                    break;
                }
            }

            if (i >= candidateCount) {
                for (i = 0; i < candidateCount; i++) {
                    if (candidates[i][0] != '\0') {
                        System.out.println(String.valueOf(candidates[i]));
                    }
                }
                System.exit(0);
            }

            for (i = 0; i < candidateCount; i++) {
                if (votes[i] == minVotes) {
                    candidates[i][0] = '\0';
                }
            }
        }
    }

    public static void resetVotes() {
        for (int i = 0; i < candidateCount; i++) {
            votes[i] = 0;
        }
    }
}
