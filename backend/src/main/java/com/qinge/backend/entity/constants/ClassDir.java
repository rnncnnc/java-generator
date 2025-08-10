package com.qinge.backend.entity.constants;

import java.io.File;

/**
 * @Data: 2025/5/31 19:51
 * @Author: Lc
 * @Description:
 */


public class ClassDir {

    public static final String TEMP_DIR = System.getProperty("user.dir") + File.separator + "temp";

    public static final String TEMPLATE_DIR = System.getProperty("user.dir") + File.separator + "template";

    public static final String BACKUP_DIR = System.getProperty("user.dir") + File.separator + "backup";

    public static final String ENTITY_DIR = "entity";

    public static final String ENTITY_POJO_DIR = "entity" + File.separator + "pojo";

    public static final String ENTITY_QUERY_DIR = "entity" + File.separator + "query";

    public static final String ENTITY_VO_DIR = "entity" + File.separator + "vo";

    public static final String MAPPER_DIR = "mapper";

    public static final String MAPPER_XML_DIR = "mapper" + File.separator + "xml";

    public static final String SERVICE_DIR = "service";

    public static final String SERVICE_IMPL_DIR = "service" + File.separator + "impl";

    public static final String CONTROLLER_DIR = "controller";
}
