package ro.danix.first.controller.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author danix
 */
@Component
public class QueryUtils {
    public static final String SEPARATOR = ",";
    public static final String SEPARATOR_AMPER = "&";
    public static final String QUESTIONMARK = "?";
    public static final String OP = "=";
    
    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String SORT_BY = "sortBy";
    public static final String SORT_ORDER = "sortOrder";
    public static final String Q_SORT_BY = QUESTIONMARK + SORT_BY + QueryUtils.OP;
    public static final String S_ORDER = QueryUtils.SEPARATOR_AMPER + QueryUtils.SORT_ORDER + QueryUtils.OP;
    public static final String S_ORDER_ASC = S_ORDER + Sort.Direction.ASC.name();
    public static final String S_ORDER_DESC = S_ORDER + Sort.Direction.DESC.name();
    
    public Pageable createPagination(final int page, final int size, final String sortBy, final String sortOrder) {
        Sort.Order order = new Sort.Order(Sort.Direction.valueOf(sortOrder), sortBy);
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(page, size, sort);
        return pageable;
    }
}
