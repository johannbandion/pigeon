//package at.kaindorf;
//
//import org.eclipse.microprofile.reactive.messaging.Channel;
//import org.eclipse.microprofile.reactive.messaging.Emitter;
//
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.util.UUID;
//
//@Path("/quickstart")
//public class QuickstartRest {
//    @Channel("quickout")
//    Emitter<String> quoteRequestEmitter;
//
//    @POST
//    @Produces(MediaType.TEXT_PLAIN)
//    public String createRequest() {
//        UUID uuid = UUID.randomUUID();
//        quoteRequestEmitter.send(uuid.toString());
//        return uuid.toString();
//    }
//
//}
