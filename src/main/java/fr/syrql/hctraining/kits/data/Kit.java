package fr.syrql.hctraining.kits.data;

public class Kit {

    private String kitName;
    private KItems[] stuffs;
    private KItems[] armor;

    public Kit(String kitName, KItems[] stuffs, KItems[] armor) {
        this.kitName = kitName;
        this.stuffs = stuffs;
        this.armor = armor;
    }

    public String getKitName() {
        return kitName;
    }

    public void setKitName(String kitName) {
        this.kitName = kitName;
    }

    public KItems[] getStuffs() {
        return stuffs;
    }

    public void setStuffs(KItems[] stuffs) {
        this.stuffs = stuffs;
    }

    public KItems[] getArmor() {
        return armor;
    }

    public void setArmor(KItems[] armor) {
        this.armor = armor;
    }
}
