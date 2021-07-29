package xf.aragorn.chain;

public final class ApplicationFilterChain implements FilterChain {

    // Used to enforce requirements of SRV.8.2 / SRV.14.2.5.1
    private static final ThreadLocal<String> lastServicedRequest;
    private static final ThreadLocal<Integer> lastServicedResponse;

    static {
        lastServicedRequest = new ThreadLocal<>();
        lastServicedResponse = new ThreadLocal<>();
    }

    // -------------------------------------------------------------- Constants


    public static final int INCREMENT = 10;


    // ----------------------------------------------------- Instance Variables

    /**
     * Filters.
     */
    private ApplicationFilterConfig[] filters = new ApplicationFilterConfig[0];


    /**
     * The int which is used to maintain the current position
     * in the filter chain.
     */
    private int pos = 0;


    /**
     * The int which gives the current number of filters in the chain.
     */
    private int n = 0;

    public ApplicationFilterChain(BusinessService businessService){
        this.businessService = businessService;
    }
    /**
     * Static class array used when the SecurityManager is turned on and
     * <code>doFilter</code> is invoked.
     */
    private static final Class<?>[] classType = new Class[]{
            String.class, Integer.class, FilterChain.class};

    /**
     * Static class array used when the SecurityManager is turned on and
     * <code>service</code> is invoked.
     */
    private static final Class<?>[] classTypeUsedInService = new Class[]{
            String.class, Integer.class};


    @Override
    public void doFilter(String request, Integer response) {
        internalDoFilter(request, response);
    }

    private void internalDoFilter(String request,
                                  Integer response) {

        // Call the next filter if there is one
        if (pos < n) {
            ApplicationFilterConfig filterConfig = filters[pos++];
            Filter filter = filterConfig.getFilter();

            filter.doFilter(request, response, this);
            return;
        }

        // We fell off the end of the chain -- call the servlet instance
        lastServicedRequest.set(request);
        lastServicedResponse.set(response);

        /* do something business */
        businessService.doSomeThing(request,response);
    }

    BusinessService businessService;

    /**
     * The last request passed to a servlet for servicing from the current
     * thread.
     *
     * @return The last request to be serviced.
     */
    public static String getLastServicedRequest() {
        return lastServicedRequest.get();
    }


    /**
     * The last response passed to a servlet for servicing from the current
     * thread.
     *
     * @return The last response to be serviced.
     */
    public static Integer getLastServicedResponse() {
        return lastServicedResponse.get();
    }


    // -------------------------------------------------------- Package Methods

    void addFilter(Filter filter){

    }

    /**
     * Add a filter to the set of filters that will be executed in this chain.
     *
     * @param filterConfig The FilterConfig for the servlet to be executed
     */
    void addFilter(ApplicationFilterConfig filterConfig) {

        // Prevent the same filter being added multiple times
        for (ApplicationFilterConfig filter : filters)
            if (filter == filterConfig)
                return;

        if (n == filters.length) {
            ApplicationFilterConfig[] newFilters =
                    new ApplicationFilterConfig[n + INCREMENT];
            System.arraycopy(filters, 0, newFilters, 0, n);
            filters = newFilters;
        }
        filters[n++] = filterConfig;

    }


    /**
     * Release references to the filters and wrapper executed by this chain.
     */
    void release() {
        for (int i = 0; i < n; i++) {
            filters[i] = null;
        }
        n = 0;
        pos = 0;
    }


    /**
     * Prepare for reuse of the filters and wrapper executed by this chain.
     */
    void reuse() {
        pos = 0;
    }


}

