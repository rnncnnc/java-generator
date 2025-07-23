package com.qinge.backend.entity.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.annotation.Resource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Data: 2025/6/5 19:15
 * @Author: Lc
 * @Description:
 */


public enum ClassTypes {
    // 基础类型
    STRING("String", String.class.getName()),
    INTEGER("Integer", Integer.class.getName()),
    FLOAT("Float", Float.class.getName()),
    DOUBLE("DOUBLE", Double.class.getName()),
    LONG("Long", Long.class.getName()),
    BOOLEAN("Boolean", Boolean.class.getName()),
    SHORT("Short", Short.class.getName()),
    BYTE("Byte", Byte.class.getName()),
    CHAR("Character", Character.class.getName()),
    OBJECT("Object", Object.class.getName()),
    DATE("Date", Date.class.getName()),
    BIGDECIMAL("BigDecimal", java.math.BigDecimal.class.getName()),
    BIGINTEGER("BigInteger", java.math.BigInteger.class.getName()),
    ARRAY("Array", Array.class.getName()),
    COLLECTION("Collection", Collection.class.getName()),
    MAP("Map", Map.class.getName()),
    ITERATOR("Iterator", Iterator.class.getName()),
    ENUM("Enum", Enum.class.getName()),
    ANNOTATION("Annotation", Annotation.class.getName()),
    CLASS("Class", Class.class.getName()),
    EXCEPTION("Exception", Exception.class.getName()),
    IOEXCEPTION("IOException", IOException.class.getName()),
    SERIALIZABLE("Serializable", Serializable.class.getName()),
    CLONEABLE("Cloneable", Cloneable.class.getName()),
    COMPARABLE("Comparable", Comparable.class.getName()),
    COMPARATOR("Comparator", Comparator.class.getName()),
    RUNNABLE("Runnable", Runnable.class.getName()),
    CALLABLE("Callable", Callable.class.getName()),
    FUTURE("Future", Future.class.getName()),

    LIST("List", List.class.getName()),
    SET("Set", Set.class.getName()),
    QUEUE("Queue", Queue.class.getName()),
    DEQUE("Deque", Deque.class.getName()),
    ARRAYLIST("ArrayList", ArrayList.class.getName()),
    HASHMAP("HashMap", HashMap.class.getName()),
    LINKEDLIST("LinkedList", LinkedList.class.getName()),
    ARRAYDEQUE("ArrayDeque", ArrayDeque.class.getName()),
    HASHSET("HashSet", HashSet.class.getName()),
    LINKEDHASHSET("LinkedHashSet", LinkedHashSet.class.getName()),
    TREEMAP("TreeMap", TreeMap.class.getName()),
    TREESET("TreeSet", TreeSet.class.getName()),
    PRIORITYQUEUE("PriorityQueue", PriorityQueue.class.getName()),
    ARRAYSTACK("ArrayStack", ArrayStack.class.getName()),
    ARRAYBLOCKINGQUEUE("ArrayBlockingQueue", ArrayBlockingQueue.class.getName()),
    LINKEDBLOCKINGQUEUE("LinkedBlockingQueue", LinkedBlockingQueue.class.getName()),
    BLOCKINGQUEUE("BlockingQueue", BlockingQueue.class.getName()),
    BLOCKINGDEQUE("BlockingDeque", BlockingDeque.class.getName()),
    COLLECTIONUTILS("CollectionUtils", CollectionUtils.class.getName()),



    ENTITY("Entity",Entity .class.getName()),
    TABLE("Table", Table.class.getName()),
    SCHEMA("Schema", Schema.class.getName()),
    COLUMN("Column", Column.class.getName()),
    OPERATION("Operation", Operation.class.getName()),
    TAG("Tag", Tag.class.getName()),
    ID("Id", Id.class.getName()),
    OPENAPI("OpenAPI", OpenAPI.class.getName()),
    INFO("Info", Info.class.getName()),

    RESTCONTROLLER("RestController", RestController.class.getName()),
    CONTROLLER("Controller", Controller.class.getName()),
    SERVICE("Service", Service.class.getName()),
    REQUESTMAPPING("RequestMapping", RequestMapping.class.getName()),
    RESOURCE("Resource", Resource.class.getName()),
    GETMAPPING("GetMapping", GetMapping.class.getName()),
    POSTMAPPING("PostMapping", PostMapping.class.getName()),
    DELETEMAPPING("DeleteMapping", DeleteMapping.class.getName()),
    PUTMAPPING("PutMapping", PutMapping.class.getName()),
    PATCHMAPPING("PatchMapping", PatchMapping.class.getName()),
    REQUESTBODY("RequestBody", RequestBody.class.getName()),
    REQUESTPARAM("RequestParam", RequestParam.class.getName()),
    PATHVARIABLE("PathVariable", PathVariable.class.getName()),
    CONFIGURATION("Configuration", Configuration.class.getName()),
    BEAN("Bean", Bean.class.getName()),

    JSONFORMATE("JsonFormat", JsonFormat.class.getName()),
    DATETIMEFORMATE("DateTimeFormat", DateTimeFormat.class.getName()),
    ;

    private String keyword;

    private String fullClassName;

    ClassTypes(String keyword, String fullClassName) {
        this.keyword = keyword;
        this.fullClassName = fullClassName;
    }

    ClassTypes() {
    }

    public static String getByKeyword(String keyword) {
        for (ClassTypes value : ClassTypes.values()) {
            if (value.getKeyword().equals(keyword)) {
                return value.getFullClassName();
            }
        }
        return null;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getFullClassName() {
        return fullClassName;
    }
}
