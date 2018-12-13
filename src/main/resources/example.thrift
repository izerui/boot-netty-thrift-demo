namespace java com.example.client
namespace py com.example.client

struct UserName {
  1: required string name;
}

service ExampleService {
  string hello(1: UserName userName);
}
