package manager;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

/**
 * Keyboard manager manages the general input of the keyboard
 * @author kchri, laurinelias
 *
 */
public class KeyboardManager {
	
	private HashMap<KeyCode, Boolean> keys;
	
	/**
	 * Constructor
	 */
	public KeyboardManager() {
		keys = new HashMap<KeyCode, Boolean>();
	}

	public HashMap<KeyCode, Boolean> getKeys() {
		return keys;
	}
	
	/**
	 * True when key is pressed, false when not
	 * @param key
	 * @return if pressed
	 */
	public boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false); 
	}
	
	/**
	 * Init listener for keyboard inputs.
	 * when key is pressed, the "keys" hasmap will be updated
	 * @param scene
	 */
	public void initListener(Scene scene) {
		scene.setOnKeyPressed(e -> getKeys().put(e.getCode(), true));
		scene.setOnKeyReleased(e -> getKeys().put(e.getCode(), false));
	}

}
