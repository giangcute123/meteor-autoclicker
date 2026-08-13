package com.tenban.autoclicker.modules;

import com.tenban.autoclicker.AutoclickerCategory;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;

public class HoldClickModule extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Integer> cps = sgGeneral.add(new IntSetting.Builder()
        .name("cps")
        .description("So click moi giay khi giu chuot.")
        .defaultValue(10)
        .range(1, 20)
        .sliderRange(1, 20)
        .build()
    );

    private final Setting<Boolean> leftClick = sgGeneral.add(new BoolSetting.Builder()
        .name("click-trai")
        .description("Tu dong click chuot trai khi giu.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> rightClick = sgGeneral.add(new BoolSetting.Builder()
        .name("click-phai")
        .description("Tu dong click chuot phai khi giu.")
        .defaultValue(false)
        .build()
    );

    private int tickCounter = 0;

    public HoldClickModule() {
        super(AutoclickerCategory.AUTOCLICKER, "hold-click",
            "Tu dong click lien tuc CHI KHI ban dang giu chuot. Tha ra la dung ngay, khong tu dao/farm khi AFK.");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (mc.player == null || mc.interactionManager == null) return;

        int ticksPerClick = Math.max(1, 20 / cps.get());
        tickCounter++;

        if (tickCounter >= ticksPerClick) {
            tickCounter = 0;

            if (leftClick.get() && mc.options.attackKey.isPressed()) {
                if (mc.crosshairTarget instanceof BlockHitResult blockHit) {
                    mc.interactionManager.attackBlock(blockHit.getBlockPos(), blockHit.getSide());
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
            }

            if (rightClick.get() && mc.options.useKey.isPressed()) {
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }
}
