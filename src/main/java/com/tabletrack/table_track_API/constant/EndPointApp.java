package com.tabletrack.table_track_API.constant;

public class EndPointApp {
    public static final String STORE = "/api/store";
    public static final String CUSTOMER = "/api/customer";
    public static final String PRODUCT = "/api/product";
    public static final String ORDER = "/api/order";
    public static final String ORDER_DETAIL = "/api/order-detail";

    public static final String AUTHENTICATION = "/api/auth";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String CONTAINER = "/api/container";
    public static final String IMPORT = "/api/import";
    public static final String RAW_MATERIAL = "/api/raw-material";
    public static final String WAREHOUSE = "/api/warehouse";

    public static final String IMPORT_DETAIL = "/api/import-detail";

    public static final String GET_ALL = "/all";
    public static final String GET_BY_ID = "/{id}";
    public static final String PUT_BY_ID = "/{id}";
    public static final String DELETE_BY_ID = "/{id}";
    public static final String FILL_BY_ID = "/fill/{id}";
    public static final String DETACH_BY_ID = "/detach/{id}";
    public static final String MOVE_FROM_ID = "/move/{id}";
    public static final String FIND_CONTAINER_ID = "/find-container/{warehouseId}/{containerId}";
    public static final String ADD_CONTAINER_ID = "/add-container/{warehouseId}/{containerId}";
    public static final String GET_LIST_CONTAINER_BY_ID = "/list-containers/{id}";
}
