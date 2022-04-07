### DSL 学习备份

```aidl
GET _search
{
  "query": {
    "match_all": {}
  }
}

# 测试分词器（默认分词器）
POST /_analyze
{
  "analyzer": "standard",
  "text": "上海的java程序员找工作太难了"
}

# 测试ik分词器（ik_smart模式）
POST /_analyze
{
  "analyzer": "ik_smart",
  "text": "上海的java程序员找工作太难了"
}

# 测试ik分词器（ik_max_word模式）
POST /_analyze
{
  "analyzer": "ik_max_word",
  "text": "上海的java程序员找工作太难了"
}

# 创建索引库
PUT /test
{
  "mappings": {
    "properties": {
      "info": {
        "type": "text",
        "analyzer": "ik_smart"
      },
      "email": {
        "type": "keyword",
        "index": false
      },
      "name": {
        "properties": {
          "firstname": {
            "type": "keyword",
            "index": false
          },
          "lastname": {
            "type": "keyword",
            "index": false
          }
        }
      }
    }
  }
}

# 查询索引库
GET /test

# 修改索引库
PUT /test/_mapping
{
  "properties":{
    "age": {
      "type": "integer",
      "index": false
    }
  }
}

# 删除索引库
DELETE /test

# 增加文档
PUT /test/_doc/1
{
  "info": "java程序员开发工程师",
  "email": "wb@qq.com",
  "name": {
    "firstName": "凡",
    "lastName": "依"
  }
}

# 查询文档
GET /test/_doc/1

# 修改文档（增量修改，针对字段进行修改）
POST /test/_update/1
{
  "doc": {
    "email": "wubing@qq.com"
  }
}


# 删除文档
DELETE /test/_doc/1


# 新建Hotel索引
PUT /hotel
{
  "mappings": {
    "properties": {
      "id": {
        "type": "keyword"
      },
      "name": {
        "type": "text",
        "analyzer": "ik_max_word",
        "copy_to": "all"
      },
      "address": {
        "type": "keyword",
        "index": false
      },
      "price": {
        "type": "integer"
      },
      "score": {
        "type": "integer"
      },
      "brand": {
        "type": "keyword",
        "copy_to": "all"
      },
      "city": {
        "type": "keyword"
      },
      "starName": {
        "type": "keyword"
      },
      "business": {
        "type": "keyword",
        "copy_to": "all"
      },
      "location": {
        "type": "geo_point"
      },
      "pic": {
        "type": "keyword",
        "index": false
      },
      "all": {
        "type": "text",
        "analyzer": "ik_max_word"
      }
    }
  }
}


# 查询Hotel索引
GET /hotel


# 删除Hotel索引库
DELETE /hotel


# 查询文档
GET /hotel/_doc/61083

# 批量查询文档
GET /hotel/_search

# 删除文档
DELETE /hotel/_doc/61083

# 查询所有 mathc_all
GET /hotel/_search
{
  "query": {
    "match_all": {}
  }
}

# 查询 match
GET /hotel/_search
{
  "query": {
    "match": {
      "all": "如家"
    }
  }
}

# 查询 multi_match
GET /hotel/_search
{
  "query": {
    "multi_match": {
      "query": "如家",
      "fields": ["name","brand","business"]
    }
  }
}


# 查询 ids
GET /hotel/_search
{
  "query": {
    "ids": {
      "values": ["2359697", "1455383931"]
    }
  }
}

# 查询 range
GET /hotel/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 200,
        "lte": 250
      }
    }
  }
}

# 查询 term
GET /hotel/_search
{
  "query": {
    "term": {
      "starName": {
        "value": "二钻"
      }
    }
  }
}


# 查询geo_bounding_box (矩形范围查询)
GET /hotel/_search
{
  "query": {
    "geo_bounding_box": {
      "location":{
        "top_left":{ 
          "lat":31.1,
          "lon":121.5
        },
        "bottom_right":{
          "lat":30.9,
          "lon":121.7
        }
      }
    }
  }
}


# 查询 geo_distance (距离范围查询)
GET /hotel/_search
{
  "query": {
    "geo_distance": {
      "distance": "2km",
      "location": "31.21,121.5"
    }
  }
}

GET /hotel/_search
{
  "query": {
    "geo_distance": {
      "distance": "2km",
      "location": {
        "lat": 31.21,
        "lon": 121.5
      }
    }
  }
}


GET /hotel

#
GET /hotel/_search
{
  "query": {
    "multi_match": {
      "query": "上海",
      "fields": ["city"]
    }
  }
}

# 查询 bool
GET /hotel/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "city": "上海"
          }
        }
      ],
      "should": [
        {
          "range": {
            "score": {
              "lte": 40
            }
          }
        },
        {
          "term": {
            "starName": "四钻"
          }
        },
        {
          "term": {
            "starName": "五钻"
          }
        }
      ],
      "must_not": [
        {
          "range": {
            "price": {
              "gte": 250
            }
          }
        }
      ],
      "filter": [
        {
          "range": {
            "price": {
              "lte": 200
            }
          }
        }
      ]
    }
  }
}


# 查询 function score
GET /hotel/_search
{
  "query": {
    "function_score": {
      "query": {
        "match_all": {}
      },
      "functions": [
        {
          "filter": {
            "term": {
              "brand": "如家"
            }
          },
          "weight": 2
        }
      ],
      "boost_mode": "sum"
    }
  }
}

# 普通字段排序
GET /hotel/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "score": {
        "order": "desc"
      },
      "price": "asc"
    }
  ]
}

# 地理位置排序
GET /hotel/_search
{
  "query": {"match_all": {}},
  "sort": [
    {
      "_geo_distance": {
        "location": {
          "lat": 31,
          "lon": 121
        },
        "order": "asc",
        "unit": "km"
      }
    }
  ]
}

# bool查询
GET /hotel/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "city": "上海"
          }
        }
      ],
      "should": [
        {
          "range": {
            "price": {
              "lte": 200
            }
          }
        }
      ],
      "must_not": [
        {
          "match": {
            "brand": "如家"
          }
        }
      ],
      "filter": [
        {
          "geo_distance": {
            "location": {
              "lat": 31.21,
              "lon": 121.5
            }, 
            "distance": "10km"
          }
        },
        {
          "match": {
            "all": "连锁"
          }
        }
      ]
    }
  }
}

GET /hotel/_search
{
  "query": {"match_all": {}},
  "sort": [
    {
      "price": {
        "order": "asc"
      }
    }
  ]
}

# 复合查询
GET /hotel/_search
{
  "query": {
    "function_score": {
      "query": {
        "bool": {
          "filter": [
            {
              "term": {
                "city": "上海"
              }
            },
            {
              "match": {
                "all": "连锁"
              }
            }
          ]
        }
      },
      "functions": [
        {
          "filter": {
            "term": {
              "isAD": true
            }
          },
          "weight": 10
        }
      ],
      "boost_mode": "sum"
    }
  },
  "sort": [
    {
      "_geo_distance": {
        "location": {
          "lat": 31.185627,
          "lon": 121.515687
        },
        "unit": "km",
        "order": "asc"
      },
      "price": {
        "order": "asc"
      }
    }
  ],
  "highlight": {
    "fields": {
      "name": {
        "require_field_match": "false"
      }
    }
  }
}

GET /hotel/_search
{
  "query": {"term": {
    "city": {
      "value": "深圳"
    }
  }},
  "from": 0
  , "size": 1
}

# 添加广告标识
POST /hotel/_update/517915
{
  "doc": {
    "isAD": false
  }
}


# 查询
GET /hotel/_doc/517915


# bucket聚合
GET /hotel/_search
{
  "size": 0,
  "aggs": {
    "brandAgg": {
      "terms": {
        "field": "brand",
        "order": {
          "_count": "asc"
        }, 
        "size": 100
      }
    }
  }
}

# bucket聚合，限定聚合范围
GET /hotel/_search
{
  "query": {
    "term": {
      "city": {
        "value": "深圳"
      }
    }
  }, 
  "size": 0,
  "aggs": {
    "brandAgg": {
      "terms": {
        "field": "brand",
        "order": {
          "_count": "asc"
        }, 
        "size": 100
      }
    }
  }
}

# Metric聚合 
GET /hotel/_search
{
  "query": {
    "term": {
      "city": {
        "value": "上海"
      }
    }
  },
  "size": 0,
  "aggs": {
    "brandAgg": {
      "terms": {
        "field": "brand",
        "order": {
          "scoreAvg.value": "desc"
        }, 
        "size": 100
      },
      "aggs": {
        "scoreAvg": {
          "avg": {
            "field": "score"
          }
        }
      }
    }
  }
}


GET /

# 测试拼音分词器
GET /_analyze
{
  "text": ["如家酒店还不错"],
  "analyzer": "pinyin"
}


DELETE /test


# 自定义分词器
PUT /test
{
  "settings": {
    "analysis": {
      "analyzer": { 
        "my_analyzer": { 
          "tokenizer": "ik_max_word",
          "filter": "py"
        }
      },
      "filter": {
        "py": { 
          "type": "pinyin",
          "keep_full_pinyin": false,
          "keep_joined_full_pinyin": true,
          "keep_original": true,
          "limit_first_letter_length": 16,
          "remove_duplicated_term": true,
          "none_chinese_pinyin_tokenize": false
        }
      }
    }
  }
}

POST /test/_doc/1
{
  "id": 1,
  "name": "狮子"
}

POST /test/_doc/2
{
  "id": 2,
  "name": "虱子"
}

GET /test/_search
{
  "query": {
    "match": {
      "name": "掉入狮子笼咋办"
    }
  }
}

DELETE /test

# 自动补全的索引库
PUT test
{
  "mappings": {
    "properties": {
      "title":{
        "type": "completion"
      }
    }
  }
}

# 示例数据
POST test/_doc
{
  "title": [
    "Sony",
    "WH-1000XM3"
  ]
}

POST test/_doc
{
  "title": [
    "SK-II",
    "PITERA"
  ]
}

POST test/_doc
{
  "title": [
    "Nintendo",
    "switch"
  ]
}

# suggest 查询
GET /test/_search
{
  "suggest": {
    "title_suggest": {
      "text": "s",
      "completion": {
        "field": "title",
        "skip_duplicates": true,
        "size": 10
      }
    }
  }
}

DELETE /hotel

# 修改酒店索引库
PUT /hotel
{
  "settings": {
    "analysis": {
      "analyzer": {
        "text_anlyzer": {
          "tokenizer": "ik_max_word",
          "filter": "py"
        },
        "completion_analyzer": {
          "tokenizer": "keyword",
          "filter": "py"
        }
      },
      "filter": {
        "py": {
          "type": "pinyin",
          "keep_full_pinyin": false,
          "keep_joined_full_pinyin": true,
          "keep_original": true,
          "limit_first_letter_length": 16,
          "remove_duplicated_term": true,
          "none_chinese_pinyin_tokenize": false
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "id": {
        "type": "keyword"
      },
      "name": {
        "type": "text",
        "analyzer": "text_anlyzer",
        "search_analyzer": "ik_smart",
        "copy_to": "all"
      },
      "address": {
        "type": "keyword",
        "index": false
      },
      "price": {
        "type": "integer"
      },
      "score": {
        "type": "integer"
      },
      "brand": {
        "type": "keyword",
        "copy_to": "all"
      },
      "city": {
        "type": "keyword"
      },
      "starName": {
        "type": "keyword"
      },
      "business": {
        "type": "keyword",
        "copy_to": "all"
      },
      "location": {
        "type": "geo_point"
      },
      "pic": {
        "type": "keyword",
        "index": false
      },
      "all": {
        "type": "text",
        "analyzer": "text_anlyzer",
        "search_analyzer": "ik_smart"
      },
      "suggestion": {
        "type": "completion",
        "analyzer": "completion_analyzer"
      }
    }
  }
}

# 测试酒店自定补全DSL
GET /hotel/_search
{
  "suggest": {
    "test_suggestion": {
      "text": "hz",
      "completion": {
        "field": "suggestion",
        "skip_duplicates": true,
        "size": 10
      }
    }
  }
}


























```