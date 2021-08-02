package friend.capybara.event.events;

import friend.capybara.event.Event;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;

public class EventSignBlockEntityRender extends Event {

    private final BlockEntity signBlockEntity;

    public EventSignBlockEntityRender(SignBlockEntity signBlockEntity) {
        this.signBlockEntity = signBlockEntity;
    }

    public BlockEntity getBlockEntity() {
        return signBlockEntity;
    }
}
