dataSourceKey: ${sourceKey}
destination: canal
groupId: esGroup
esMapping:
   _index: ${indexName}
   _type: doc
   _id: ID
#  upsert: true
#  pk: _id
   sql: "${sql}"
#  objFields:
#   _labels: array:;
# etlCondition: "where a.c_time>='{0}'"
   commitBatch: 30
