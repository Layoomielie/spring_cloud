input {
${0}
}
filter {

}
output {
elasticsearch {
hosts => "${1}:9200"
index => "%{tbName}"
document_id => "%{id}"
}
}