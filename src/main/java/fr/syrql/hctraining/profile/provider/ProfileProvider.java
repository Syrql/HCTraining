package fr.syrql.hctraining.profile.provider;

import fr.syrql.hctraining.HCTraining;
import fr.syrql.hctraining.arena.data.Arena;
import fr.syrql.hctraining.profile.data.Profile;
import fr.syrql.hctraining.utils.FileUtils;
import fr.syrql.hctraining.utils.IOUtil;
import fr.syrql.hctraining.utils.io.provide.IProvider;
import fr.syrql.hctraining.utils.io.readable.IReadable;
import fr.syrql.hctraining.utils.io.writable.IWritable;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileProvider implements IProvider<UUID, Profile>, IWritable, IReadable {

    private final HCTraining hcTraining;
    private Map<UUID, Profile> profiles;
    private File save;

    public ProfileProvider(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
        this.profiles = new HashMap<>();
        this.save = new File(this.hcTraining.getDataFolder(), "/profiles/");
    }


    @Override
    public void provide(UUID key, Profile value) {
        this.profiles.put(key, value);
        this.write();
    }

    @Override
    public void remove(UUID key) {
        Profile user = this.profiles.get(key);

        if (user == null) return;

        this.profiles.remove(key);
        this.write();
    }

    @Override
    public Profile get(UUID key) {
        return this.profiles.get(key);
    }

    @Override
    public void read() {

        File[] files = save.listFiles();

        if (files == null) {
            this.profiles = new HashMap<>();
            return;
        }
        IOUtil ioUtil = this.hcTraining.getIoUtil();

        for (File file : files) {
            if (file.isFile()) {
                final String json = FileUtils.loadContent(file);
                Profile user = ioUtil.deserializeProfile(json);
                this.profiles.put(user.getUuid(), user);
            }
        }

    }

    @Override
    public void write() {

        if (this.profiles == null) return;

        final IOUtil ioUtil = this.hcTraining.getIoUtil();

        for (Profile user : this.getProfiles()) {
            final File file = new File(save, user.getUuid() + ".json");
            final String json = ioUtil.serializeProfile(user);
            FileUtils.save(file, json);
        }
    }

    public Collection<Profile> getProfiles() {
        return this.profiles.values();
    }
}
