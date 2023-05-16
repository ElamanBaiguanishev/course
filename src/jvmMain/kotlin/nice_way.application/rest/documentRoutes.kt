package nice_way.application.rest

import config.Config
import data.BibTex
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nice_way.application.mongo.collection
import org.litote.kmongo.eq
import org.litote.kmongo.findOne

fun Route.documentRoutes() {
    route(Config.documentsPath) {
        get {
            val a = collection.find().toList()
            call.respond(a)
        }
        get("{id}") {
            val id =
                call.parameters["id"] ?: return@get call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
            val bibTex = collection.findOne(BibTex::_id eq id) ?: return@get call.respondText(
                "No element with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(bibTex)
        }
        post {
//            collection.insertOne(call.receive<BibTex>())
            call.respond(HttpStatusCode.OK)
        }
        delete("/{author}") {
            val author = call.parameters["author"] ?: error("Invalid delete request")
//            collection.deleteOne(BibTex::author eq author)
            call.respond(HttpStatusCode.OK)
        }
    }
}