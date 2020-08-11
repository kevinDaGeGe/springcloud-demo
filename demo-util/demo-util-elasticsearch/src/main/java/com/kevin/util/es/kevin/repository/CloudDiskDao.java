package com.kevin.util.es.kevin.repository;

import com.kevin.util.es.kevin.entity.CloudDiskEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface CloudDiskDao extends ElasticsearchRepository<CloudDiskEntity, String> {

}
