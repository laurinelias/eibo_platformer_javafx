package enums;

/**
 * Represents GameObjects for the level builder (text files)
 * @author kchri, laurinelias
 *
 */
public enum Obstacle {
	GROUND("G"), SPIKE("S"), BALL("B"), GLASS("Q"), SPRING("J"), SLOW("L"), SPEED("F");
	
	private String typeChar = "";
	
	private Obstacle(String typeChar) {
		this.typeChar = typeChar;
	}

	public String getTypeChar() {
		return typeChar;
	}
}
