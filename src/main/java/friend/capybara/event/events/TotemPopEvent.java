package friend.capybara.event.events;

import friend.capybara.event.Event;
import net.minecraft.entity.Entity;

public class TotemPopEvent extends Event {

    private Entity entity;

    public TotemPopEvent(Entity entity) {
        super();
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
