package fr.syrql.hctraining.kits.provider;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.arena.data.Arena;
import fr.syrql.hctraining.kits.data.Kit;
import fr.syrql.hctraining.utils.io.provide.IProvider;
import fr.syrql.hctraining.utils.io.readable.IReadable;
import fr.syrql.hctraining.utils.io.writable.IWritable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class KitProvider implements IProvider<String, Arena>, IWritable, IReadable {

    private final HCTraining hcTraining;
    private Map<String, Kit> kits;
    private File path;

    public KitProvider(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
        this.kits = new HashMap<>();
        this.path = new File(this.hcTraining.getDataFolder(), "/kits/");
    }

    @Override
    public void provide(String key, Arena value) {

    }

    @Override
    public void remove(String key) {

    }

    @Override
    public Arena get(String key) {
        return null;
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }
}
