package com.tenban.autoclicker;

import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.systems.modules.Modules;
import com.tenban.autoclicker.modules.HoldClickModule;

public class AutoclickerAddon extends MeteorAddon {
    @Override
    public void onInitialize() {
        Modules.get().add(new HoldClickModule());
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(AutoclickerCategory.AUTOCLICKER);
    }

    @Override
    public String getPackage() {
        return "com.tenban.autoclicker";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("giangcute123", "meteor-autoclicker");
    }
}
