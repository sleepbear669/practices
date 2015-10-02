

## 실습
 
특정 Directory의 파일들의 변경을 감시하는 프로그램을 작성한다

- 프로그램이 실행될때 디렉토리 경로를 입력받는다
- 프로그램이 실행되면 해당 디렉토리의 모든 파일을 출력한다
    - sub dicrectory의 파일까지 모두 포함한다
    - directory는 출력목록에서 제외한다
    - format : `relative_path : file_size`
- 강제 종료할 때까지 프로그램은 실행되며 실행되는 동안 해당 디렉토리의 파일 변화를 모니터링한다
    - 파일이 추가되면 'ADDED file_path' 출력
    - 파일이 삭되제면 'DELEDED file_path' 출력
    - 파일이 변경되면 'MODIFIED file_path' 출력

