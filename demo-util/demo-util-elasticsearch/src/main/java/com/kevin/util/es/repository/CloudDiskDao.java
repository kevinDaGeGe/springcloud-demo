package com.kevin.util.es.repository;

import com.kevin.util.es.entity.CloudDiskEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface CloudDiskDao extends ElasticsearchRepository<CloudDiskEntity, String> {

}
