jdbc {
clean_run => true
id => "${0}"
jdbc_driver_library => "/opt/logstash/logstash-6.6.0/lib/mysql-connector-java-5.1.41.jar"
jdbc_driver_class => "com.mysql.jdbc.Driver"
jdbc_connection_string => "${1}"
jdbc_user => "${2}"
jdbc_password => "${3}"
statement => "${4}"
use_column_value => false
add_field => {"tbName"=>"${5}"}
}