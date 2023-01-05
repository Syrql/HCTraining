package fr.syrql.hctraining.arena.provider;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.arena.data.Arena;
import fr.syrql.hctraining.utils.FileUtils;
import fr.syrql.hctraining.utils.IOUtil;
import fr.syrql.hctraining.utils.io.provide.IProvider;
import fr.syrql.hctraining.utils.io.readable.IReadable;
import fr.syrql.hctraining.utils.io.writable.IWritable;

import java.io.File;
import java.util.*;

public class ArenaProvider implements IProvider<String, Arena>, IWritable, IReadable {

    private final HCTraining hcTraining;
    private Map<String, Arena> arenaMap;
    private final File save;

    public ArenaProvider(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
        this.arenaMap = new HashMap<>();
        this.save = new File(this.hcTraining.getDataFolder(), "/arenas");
    }

    @Override
    public void provide(String key, Arena value) {
        this.arenaMap.put(key, value);
        this.write();
    }

    @Override
    public void remove(String key) {

        Arena user = this.arenaMap.get(key);

        if (user == null) return;

        this.arenaMap.remove(key);
        this.write();
    }

    @Override
    public Arena get(String key) {
        return this.arenaMap.get(key);
    }

    @Override
    public void read() {

        File[] files = save.listFiles();

        if (files == null) {
            this.arenaMap = new HashMap<>();
            return;
        }
        IOUtil ioUtil = this.hcTraining.getIoUtil();

        for (File file : files) {
            if (file.isFile()) {
                final String json = FileUtils.loadContent(file);
                Arena user = ioUtil.deserialize(json);
                this.arenaMap.put(user.getId(), user);
            }
        }

    }

    @Override
    public void write() {

        if (this.arenaMap == null) return;

        final IOUtil ioUtil = this.hcTraining.getIoUtil();

        for (Arena user : this.getArenas()) {
            final File file = new File(save, user.getId() + ".json");
            final String json = ioUtil.serialize(user);
            FileUtils.save(file, json);
        }
    }

    public Collection<Arena> getArenas() {
        return this.arenaMap.values();
    }
}
