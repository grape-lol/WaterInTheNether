package fuck.actual.the.what;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaterInTheNether implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("water-in-the-nether");

	@Override
	public void onInitialize() {
		LOGGER.info("Loading Water In The Nether Mod");
	}
}