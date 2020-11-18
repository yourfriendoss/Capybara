/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDrinker420/bleachhack-1.14/).
 * Copyright (c) 2019 Bleach.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bleach.hack.module;

import bleach.hack.event.events.EventKeyPress;
import bleach.hack.module.mods.*;
import com.google.common.eventbus.Subscribe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private static final List<Module> mods = Arrays.asList(
        new AllahHaram(),
        new Ambience(),
        new AntiChunkBan(),
        new AntiHunger(),
        new AntiMonkey(),
        new ArrowJuke(),
        new AutoAnvil(),
        new AutoArmor(),
        new AutoBed(),
        new AutoDonkeyDupe(),
        new AutoEat(),
        new AutoFish(),
        new AutoLog(),
        new AutoNametag(),
        new AutoReconnect(),
        new AutoRespawn(),
        new AutoSign(),
        new AutoTool(),
        new AutoTotem(),
        new CrystalAura(),
        new AutoWalk(),
        new BetterPortal(),
        new BlockParty(),
        new BookCrash(),
        new BowBot(),
        new BoxESP(),
        new ChestESP(),
        new ClickGui(),
        new ClickTp(),
        new ColorSigns(),
        new ColourChooser(),
        new Criticals(),
        new CustomChat(),
        new DiscordRPCMod(),
        new Dispenser32k(),
        new Discord(),
        new DonkeyAlert(),
        new ElytraFly(),
        new ElytraReplace(),
        new ElytraSwap(),
        new EntityControl(),
        new FakeLag(),
        new FastUse(),
        new Flight(),
        new Freecam(),
        new Fullbright(),
        new Ghosthand(),
        new HandProgress(),
        new HoleESP(),
        new HoleTP(),
        new HotbarCache(),
        new Jesus(),
        new Killaura(),
        new MountBypass(),
        new MouseFriend(),
        new Nametags(),
        new Nofall(),
        new NoKeyBlock(),
        new NoRender(),
        new NoSlow(),
        new Notebot(),
        new NotebotStealer(),
        new NoToolCooldown(),
        new NoVelocity(),
        new Nuker(),
        new NukerBypass(),
        new OffhandApple(),
        new OffhandCrash(),
        new PacketFly(),
        new Peek(),
        new PlayerCrash(),
        new PopoutGUI(),
        new PortalESP(),
        new SafeWalk(),
        new Scaffold(),
        new Spammer(),
        new Speed(),
        new SpeedMine(),
        new Sprint(),
        new StashFinder(),
        new Step(),
        new Surround(),
        new TerroristNotifier(),
        new Timer(),
        new ToggleMSGs(),
        new Tracers(),
        new Trail(),
        new Trajectories(),
        new TunnelESP(),
        new VoidESP(),
        new Xray(),
        new Yaw(),
        new Zoom(),
        new CleanChat(),
        new MobOwner(),
        new TabFriends(),
        new FootXp(),
        new LiquidRemover(),
        new ShulkerView(),
        new AutoExplode(),
        new AutoTrap(),
        new AutoWither(),
        new UI()
    ).stream().sorted(Comparator.comparing(Module::getName, String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toList());

    public static List<Module> getModules() {
        return mods;
    }

    public static Module getModule(Class<? extends Module> clazz) {
        for (Module module : mods) {
            if (module.getClass().equals(clazz)) {
                return module;
            }
        }

        return null;
    }

    public static Module getModuleByName(String name) {
        for (Module m : mods) {
            if (name.equalsIgnoreCase(m.getName()))
                return m;
        }
        return null;
    }

    public static List<Module> getModulesInCat(Category cat) {
        return mods.stream().filter(m -> m.getCategory().equals(cat)).collect(Collectors.toList());
    }

    @Subscribe
    public static void handleKeyPress(EventKeyPress eventKeyPress) {
        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_F3))
            return;
        mods.stream().filter(m -> m.getKey() == eventKeyPress.getKey()).forEach(Module::toggle);
    }
}
