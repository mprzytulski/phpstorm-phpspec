package pl.projectspace.php.composer;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class Require {
    private HashMap<String, Package> packages = new HashMap<String, Package>();

    public void addPackage(Package p) {
        packages.put(p.getName(), p);
    }

    public Package getPackage(String packageName) {
        return packages.get(packageName);
    }

    public boolean hasPackage(String packageName) {
        return packages.containsKey(packageName);
    }

    public Collection<Package> getCollection() {
        return packages.values();
    }
}
