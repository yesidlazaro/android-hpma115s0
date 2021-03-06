package hpsaturn.pollutionreporter.view;

/**
 * created by antonio vanegas @hpsaturn on 7/23/18.
 */

public class FragmentPickerInfo {

    private final String name;
    private final int icon;

    public FragmentPickerInfo(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

}
