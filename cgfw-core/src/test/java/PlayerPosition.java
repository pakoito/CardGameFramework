import com.pacoworks.cardframework.api.model.components.CFWComponent;

/**
 * Created by Paco on 07/12/2014. See LICENSE.md
 */
public class PlayerPosition extends CFWComponent {
    public static final String COMPONENT_NAME = "player_position";

    public final int position;

    public PlayerPosition(int position) {
        this.position = position;
    }

    @Override
    public Float getValue() {
        return (float)position;
    }
}
