package designPatterns.exercises.n6_ticTacToe.v3.bridge.game;

import designPatterns.exercises.n6_ticTacToe.v3.bridge.utils.IO;

public class TabularBoard implements ImplementationBoard {

	private Token[][] tokens;

	public static final int DIMENSION = 3;

	public TabularBoard() {
		tokens = new Token[Board.DIMENSION][Board.DIMENSION];
		for (int i = 0; i < Board.DIMENSION; i++) {
			for (int j = 0; j < Board.DIMENSION; j++) {
				tokens[i][j] = Token.VOID;
			}
		}
	}

	@Override
	public void write() {
		for (int i = 0; i < Board.DIMENSION; i++) {
			for (int j = 0; j < Board.DIMENSION; j++) {
				IO.instance().write(tokens[i][j].getSymbol() + " ");
			}
			IO.instance().writeln();
		}
	}

	@Override
	public boolean complete() {
		int contTokens = 0;
		for (int i = 0; i < Board.DIMENSION; i++) {
			for (int j = 0; j < Board.DIMENSION; j++) {
				if (tokens[i][j] != Token.VOID) {
					contTokens++;
				}
			}
		}
		return contTokens == 6;
	}

	@Override
	public boolean existTicTacToe() {
		for (int i=0; i<Token.values().length-1; i++) {
			if (this.existTicTacToe(Token.values()[i])) {
				return true;
			}
		}
		return false;
	}

	private boolean existTicTacToe(Token token) {
		int[] rowCont = new int[Board.DIMENSION];
		int[] columnCont = new int[Board.DIMENSION];
		int diagonalCont = 0;
		int inverseCont = 0;
		for (int i = 0; i < Board.DIMENSION; i++) {
			for (int j = 0; j < Board.DIMENSION; j++) {
				if (tokens[i][j] == token) {
					rowCont[i]++;
					columnCont[j]++;
					if (i - j == 0) {
						diagonalCont++;
					}
					if (i + j == Board.DIMENSION - 1) {
						inverseCont++;
					}
				}
			}
		}
		if (diagonalCont == Board.DIMENSION || inverseCont == Board.DIMENSION) {
			return true;
		}
		for (int i = 0; i < Board.DIMENSION; i++) {
			if (rowCont[i] == Board.DIMENSION
					|| columnCont[i] == Board.DIMENSION) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean full(Coordinate coordinate, Token token) {
		return tokens[coordinate.getRow()][coordinate.getColumn()] == token;
	}

	@Override
	public void put(Coordinate coordinate, Token token) {
		tokens[coordinate.getRow()][coordinate.getColumn()] = token;
	}

}
