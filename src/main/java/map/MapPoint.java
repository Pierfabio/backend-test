package map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MapPoint {

    private int position;
    private int maxPosition;

    public int getForwardLocation() {

        if(getPosition() == getMaxPosition()){
            return 0;
        }

        return (getPosition()) % (getMaxPosition()) + 1;
    }

    public int getBackwardLocation() {
        if (getPosition() > 0) return getPosition() - 1;
        else return getMaxPosition();
    }
}
