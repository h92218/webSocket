# websocket
websocket 생성 테스트 


# @SubscribeMapping 사용
subscription에 대한 응답으로 메세지를 보내줄 수 있음

# STOMP 메세지 프레임
COMMAND
header1:value1
header2:value2
Body

- COMMAND가 SUBSCRIBE인 경우 : @SubscribeMapping 어노테이션으로 컨트롤러에서 처리가능
- COMMNAD가 SEND인 경우 : @MessageMapping 어노테이션으로 처리가능

# @MessageExceptionHandler
@MessageMapping 이나 @SubscribeMapping 메소드에서 exception던지면 받는다.
@SendToUser("/queue/errors")로 에러 구독용 채널로 메세지를 보내기도 함
메세지는 MESSAGE 커맨드로 내려감.

# Channel
1. clientInBoundChannel : client -> server 채널
2. clientOutBoundChannel : server -> client 채널
3. brokerChannel : server 내부에서 사용하는 채널
인터셉터로 채널 인터셉트
@SubscribeMapping 와 InboundChannel 중 어디를 먼저 타나
=> intercept가 먼저 되고 그다음 controller로 간다
