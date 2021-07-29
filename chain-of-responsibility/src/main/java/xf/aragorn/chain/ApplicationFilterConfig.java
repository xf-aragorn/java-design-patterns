package xf.aragorn.chain;

/**
 * @author xufeng
 * @since 2021/7/28
 */


import javax.management.ObjectName;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Implementation of a <code>javax.servlet.FilterConfig</code> useful in
 * managing the filter instances instantiated when a web application
 * is first started.
 *
 * @author Craig R. McClanahan
 */
public final class ApplicationFilterConfig implements FilterConfig, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Empty String collection to serve as the basis for empty enumerations.
     */
    private static final List<String> emptyString = Collections.emptyList();

    // ----------------------------------------------------------- Constructors


    ApplicationFilterConfig(/* Context context */Filter filter) {
        super();
        this.filter = filter;
        initFilter();
    }
    /**
     * Construct a new ApplicationFilterConfig for the specified filter
     * definition.
     */
    ApplicationFilterConfig(/* Context context */) {

        super();

        // Allocate a new filter instance if necessary
//        if (filterDef.getFilter() == null) {
        getFilter();
//        } else {
//            this.filter = filterDef.getFilter();
//            context.getInstanceManager().newInstance(filter);
//            initFilter();
//        }
    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The application Filter we are configured for.
     */
    private transient Filter filter = null;


    /**
     * JMX registration name
     */
    private ObjectName oname;

    // --------------------------------------------------- FilterConfig Methods


    /**
     * Return the name of the filter we are configuring.
     */
    @Override
    public String getFilterName() {
        return filter.getClass().getCanonicalName();
    }

    /**
     * @return The class of the filter we are configuring.
     */
    public String getFilterClass() {
        return filter.getClass().getName();
    }

    /**
     * Return a <code>String</code> containing the value of the named
     * initialization parameter, or <code>null</code> if the parameter
     * does not exist.
     *
     * @param name Name of the requested initialization parameter
     */
    @Override
    public String getInitParameter(String name) {

        return "";
    }


    /**
     * Return an <code>Enumeration</code> of the names of the initialization
     * parameters for this Filter.
     */
    @Override
    public Enumeration<String> getInitParameterNames() {

        return Collections.enumeration(emptyString);
    }


    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ApplicationFilterConfig[");
        sb.append("name=");
        sb.append(", filterClass=");
        sb.append("]");
        return sb.toString();
    }

    // --------------------------------------------------------- Public Methods

    public Map<String, String> getFilterInitParameterMap() {
        return null;
    }


    Filter getFilter() {
        // Return the existing filter instance, if any
        if (this.filter != null)
            return this.filter;
        initFilter();
        return this.filter;

    }

    private void initFilter() {
        filter.init(this);

    }

    /**
     * Release the Filter instance associated with this FilterConfig,
     * if there is one.
     */
    void release() {
        this.filter = null;
    }


    /*
     * Log objects are not Serializable.
     */
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }

}
