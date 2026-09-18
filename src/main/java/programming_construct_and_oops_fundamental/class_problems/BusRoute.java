package main.java.programming_construct_and_oops_fundamental.class_problems;
public class BusRoute {
    private final String routeCode;
    private final String routeName;
    private final int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        if (routeCode == null || routeCode.trim().isEmpty()
                || routeName == null || routeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Route details are required");
        }
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 0);
    }

    public int compareTo(BusRoute other) {
        int priorityOrder = Integer.compare(other.priority, priority);
        if (priorityOrder != 0) return priorityOrder;

        int codeOrder = routeCode.compareToIgnoreCase(other.routeCode);
        if (codeOrder != 0) return codeOrder;

        return routeName.compareToIgnoreCase(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null) return new BusRoute[0];

        BusRoute[] ranked = new BusRoute[routes.length];

        for (int i = 0; i < routes.length; i++) {
            ranked[i] = routes[i];
        }

        for (int i = 1; i < ranked.length; i++) {
            BusRoute current = ranked[i];
            int j = i - 1;

            while (j >= 0 && ranked[j].compareTo(current) > 0) {
                ranked[j + 1] = ranked[j];
                j--;
            }

            ranked[j + 1] = current;
        }

        return ranked;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);

        for (BusRoute route : ranked) {
            System.out.println(route.routeCode);
        }
    }
}