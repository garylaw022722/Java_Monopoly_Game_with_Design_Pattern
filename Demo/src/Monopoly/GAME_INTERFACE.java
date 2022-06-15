package Monopoly;

import java.util.*;

public class GAME_INTERFACE {
    int head = 0;
    int tail = 0;
    int rowInCol = 6;
    int width = 28;
    int height = 10;
    int bound = 10;
    int inner_distance = 1;
    String message = "";
    String[] dataSource = new String[4];
    ArrayList<Object> squareList;

    public GAME_INTERFACE(Game game) {
        this.squareList = game.getSquareList();
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public void displayGameBoard(int gameRound) {
        String content = "", widthLine = "", heightLine = "";
        head = (squareList.size() / 4) * 2;

        tail = head + rowInCol - 1;

        // rowSet1
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.print(getWhiteSpace(bound));
        for (int i = head; i <= tail; i++) {// num box
            if (i != tail)
                System.out.print(fromatData(String.valueOf(i + 1), " ".repeat(width)));
        }
        System.out.println();
        System.out.println(getMaxWidth());
        printHorizonalSquare(squareList, "Bottom", height - inner_distance - 1);
        System.out.println(getMaxWidth());

        // rowSet2

        for (int middle = 0; middle < 4; middle++) {
            Object[] squareLeft_right = { squareList.get(--head), squareList.get(++tail) };// iteration of left and
                                                                                           // right square
            int numSpace = width * (rowInCol - 2) - 1;
            String newLine = "";

            for (int x = 0; x < height; x++) {// print 10times of horizontail line with dataSet element .
                heightLine = getLine("|", " ");
                for (int s = 0; s < squareLeft_right.length; s++) {
                    Square pos = (Square) squareLeft_right[s];
                    prepareContentSet(pos);
                    content = getContent(x, heightLine, pos) + "|";

                    if (s == 0) { // represent : LEFT Square
                        content = addInnerLine("Left", content, pos); // reformat string according left or right
                        if (x == 4) {
                            System.out.print(fromatData(String.valueOf(head + 1), " ".repeat(bound)));
                        } else {
                            System.out.print(getWhiteSpace(bound));
                        }
                        System.out.print(content + getBoardTitle(middle, x, numSpace, gameRound));

                    } else { // represent :RightSquare
                        content = addInnerLine("Reight", content, pos);
                        System.out.print(content);
                        if (x == 4)
                            System.out.print(fromatData(String.valueOf(tail + 1), " ".repeat(bound)));
                        /// in square of line 5 will print the position of square
                    }

                }
                System.out.println();
            }
            widthLine = getLine("+", "-") + "+";
            newLine = getWhiteSpace(bound) + widthLine + getWhiteSpace(numSpace) + widthLine;
            if (middle < 3)
                System.out.println(newLine);
        }

        System.out.print(getMaxWidth() + "\n");
        tail = --head;
        head = 0;
        ArrayList<Object> rotateElement = new ArrayList<>();
        rotateElement.addAll(squareList.subList(head, tail + 1));
        Collections.reverse(rotateElement);
        printHorizonalSquare(rotateElement, "Top", inner_distance);
        System.out.print(getMaxWidth() + "\n");
        System.out.print(getWhiteSpace(bound));
        for (int s = tail; s >= head; s--) {
            if (s == tail)
                System.out.print(getWhiteSpace(width));
            else
                System.out.print(fromatData(String.valueOf(s + 1), " ".repeat(width)));
        }
        System.out.println();
        System.out.println();
        System.out.println();

    }

    public String getBoardTitle(int numCol, int heightInSqure, int numSpace, int round) {
        String title = "";
        if (numCol == 1) {
            if (heightInSqure == height / 2 + 2) {
                title = "Monopoly : " + "Round " + round;
            }
            return fromatData(title, getWhiteSpace(numSpace));
        }

        return getWhiteSpace(numSpace); ///
    }

    public void printHorizonalSquare(ArrayList<Object> squareList, String direction, int lineTo) {
        String content = "", heightLine = "";
        for (int x = 0; x < height; x++) {// height Line
            if (x == 4 && direction.equals("Top"))
                System.out.print(fromatData(String.valueOf(tail + 1), " ".repeat(bound)));
            else
                System.out.print(getWhiteSpace(bound));

            for (int i = head; i <= tail; i++) {
                Square square = (Square) squareList.get(i);
                prepareContentSet(square);
                heightLine = getLine("|", " ");
                content = getContent(x, heightLine, square);
                if (x == lineTo)
                    content = addInnerLine(direction, content, square);
                if (i == tail) {
                    content += "|";
                    if (x == 4) {
                        if (direction.equals("Bottom"))
                            content += fromatData(String.valueOf(tail + 1), " ".repeat(bound));
                    }

                }
                System.out.print(content);
            }
            System.out.println();
        }
    }

    public String addInnerLine(String direction, String line, Square s) {
        String output = "";
        int productLine_Leng, gapDistance;
        boolean isProperty = s instanceof Property;
        gapDistance = inner_distance + 4;
        productLine_Leng = line.length() - gapDistance;

        if (!isProperty)
            return line;

        if (direction.equals("Left"))
            output = line.substring(0, productLine_Leng) + "|" + line.substring(productLine_Leng + 1, line.length());

        else if (direction.equals("Reight"))
            output = line.substring(0, gapDistance - 1) + "|" + line.substring(gapDistance, line.length());
        else if (direction.equals("Top") || direction.equals("Bottom"))
            output = getLine("|", "-");

        return output;
    }

    public String getContent(int x, String heightLine, Square square) {
        String content;

        if (square instanceof Visiting) {
            if (x == 2)
                content = fromatData(dataSource[0], heightLine);
            else if (x == 4)
                content = fromatData(dataSource[2], heightLine);
            else if (x == 6)
                content = fromatData(dataSource[1], heightLine); // title: In Jail
            else if (x == 8)
                content = fromatData(dataSource[3], heightLine);
            else
                content = heightLine;
        } else {
            if (x == 3)
                content = fromatData(dataSource[0], heightLine);
            else if (x == 5)
                content = fromatData(dataSource[1], heightLine);
            else if (x == 7)
                content = fromatData(dataSource[2], heightLine); /// store position the player landing on
            else
                content = heightLine;
        }
        return content;

    }

    public String getWhiteSpace(int s) {
        return " ".repeat(s);
    }

    public String getMaxWidth() {

        String widthLine = "";
        for (int i = 0; i < rowInCol; i++) {// width kine
            widthLine += getLine("+", "-");
        }
        return getWhiteSpace(bound) + widthLine + "+";
    }

    public String getLine(String sym1, String sym2) { // notConculde ;
        String str = "";
        str += sym1 + sym2.repeat(width - 1);
        return str;
    }

    public String fromatData(String data, String line) {
        String item = "";
        int endSpace, data_Len, whiteSpace_Len;
        data_Len = data.length();
        whiteSpace_Len = (line.length() - data_Len) / 2;
        endSpace = line.length() - data_Len - whiteSpace_Len - 1;
        item = line.charAt(0) + " ".repeat(whiteSpace_Len) + data + " ".repeat(endSpace);
        return item;

    }

    public void prepareContentSet(Square square) {
        Vector<Player> playerINSquPlayers = square.getPlayerIn_Square();
        Arrays.fill(dataSource, "");

        dataSource[2] = formatPlayerLocation(playerINSquPlayers);
        if (square instanceof Property) {
            Property p = (Property) square;
            dataSource[0] = p.getName();
            dataSource[1] = p.getPrice() + " HKD";

        } else if (square instanceof Chance) {
            dataSource[0] = "?";
            dataSource[1] = "Chance";
        } else if (square instanceof FreePacking) {
            dataSource[0] = "Free";
            dataSource[1] = "Packing";
        } else if (square instanceof Go) {
            dataSource[0] = "Go";
            dataSource[1] = "Acquired $1500 if passed";
        } else if (square instanceof GO_TO_Jail) {
            dataSource[0] = "Go To Jail";
            dataSource[2] = "";
        } else if (square instanceof INCOME_TAX) {
            dataSource[0] = "INCOME TAX";
            dataSource[1] = "PAY 10%";
        } else if (square instanceof Visiting) {
            GO_TO_Jail jail = (GO_TO_Jail) squareList.get(15);
            dataSource[0] = "Just Visiting ";
            dataSource[1] = "In Jail";
            dataSource[3] = formatPlayerLocation(jail.playersInSquare);
        }

    }

    public String formatPlayerLocation(Vector<Player> playerINSquPlayers) {
        String playerList = "";
        if (playerINSquPlayers.size() == 0)
            return "";
        for (int w = 0; w < playerINSquPlayers.size(); w++) {
            String player = playerINSquPlayers.get(w).getName();
            playerList += player;
            if (w != playerINSquPlayers.size() - 1)
                playerList += ",";
        }
        return "[ " + playerList + " ]";

    }

}
