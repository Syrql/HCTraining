package fr.syrql.hctraining;

import fr.syrql.hctraining.arena.ArenaManager;
import fr.syrql.hctraining.arena.provider.ArenaProvider;
import fr.syrql.hctraining.commands.ArenaCommand;
import fr.syrql.hctraining.commands.SelectorCommand;
import fr.syrql.hctraining.listener.MatchListener;
import fr.syrql.hctraining.listener.PlayerJoinListener;
import fr.syrql.hctraining.listener.SelectorListener;
import fr.syrql.hctraining.listener.UnrankedSelectListener;
import fr.syrql.hctraining.lobby.manager.LobbyManager;
import fr.syrql.hctraining.match.manager.MatchManager;
import fr.syrql.hctraining.profile.provider.ProfileProvider;
import fr.syrql.hctraining.queue.QueueManager;
import fr.syrql.hctraining.scoreboard.ScoreboardManager;
import fr.syrql.hctraining.scoreboard.example.ExampleScoreAdapter;
import fr.syrql.hctraining.selector.manager.SelectorManager;
import fr.syrql.hctraining.task.QueueTask;
import fr.syrql.hctraining.utils.IOUtil;
import fr.syrql.hctraining.utils.config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HCTraining extends JavaPlugin {

    private ConfigManager configManager;
    private LobbyManager lobbyManager;
    private IOUtil ioUtil;
    private ArenaProvider arenaProvider;
    private SelectorManager selectorManager;
    private QueueManager queueManager;
    private ArenaManager arenaManager;
    private MatchManager matchManager;
    private ProfileProvider profileProvider;
    private ScoreboardManager scoreboardManager;
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.registerHandlers();
        this.registerCommands();
        this.registerListeners();
        this.registerTasks();

        this.scoreboardManager = new ScoreboardManager(this, new ExampleScoreAdapter(this));
        this.scoreboardManager.setUpdateInterval(2000L);
    }

    @Override
    public void onDisable() {
        this.arenaProvider.write();
        this.profileProvider.write();
        this.scoreboardManager.disable();
    }

    private void registerHandlers() {
        this.configManager = new ConfigManager(this);
        this.ioUtil = new IOUtil();

        this.arenaProvider = new ArenaProvider(this);
        this.arenaProvider.read();

        this.profileProvider = new ProfileProvider(this);
        this.profileProvider.read();

        this.arenaManager = new ArenaManager(this);
        this.matchManager = new MatchManager(this);
        this.lobbyManager = new LobbyManager(this);
        this.selectorManager = new SelectorManager(this);
        this.queueManager = new QueueManager(this);
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new SelectorListener(this), this);
        this.getServer().getPluginManager().registerEvents(new UnrankedSelectListener(this), this);
        this.getServer().getPluginManager().registerEvents(new MatchListener(this), this);
    }

    private void registerCommands() {
        new SelectorCommand(this, "selector", "permission.selector", false);
        new ArenaCommand(this, "arena", "permission.arena", false);
    }

    private void registerTasks() {
        this.getServer().getScheduler().runTaskTimerAsynchronously(this, new QueueTask(this), 1L, 20L * 10L);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }

    public ArenaProvider getArenaProvider() {
        return arenaProvider;
    }

    public SelectorManager getSelectorManager() {
        return selectorManager;
    }

    public IOUtil getIoUtil() {
        return ioUtil;
    }

    public QueueManager getQueueManager() {
        return queueManager;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public ProfileProvider getProfileProvider() {
        return profileProvider;
    }

    public MatchManager getMatchManager() {
        return matchManager;
    }
}
