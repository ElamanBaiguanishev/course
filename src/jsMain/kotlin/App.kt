import components.load.CCurrentBibtex
import components.load.CBibtexList
import components.load.CInputFileBibtex
import components.find.CReadCriterion
import config.Config
import csstype.ClassName
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.footer
import react.dom.html.ReactHTML.header
import react.dom.html.ReactHTML.nav
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import react.router.dom.Link
import tanstack.query.core.QueryClient
import tanstack.react.query.QueryClientProvider

fun main() {
    val container = web.dom.document.getElementById("root")!!
    createRoot(container).render(app.create())
}

val app = FC<Props>("App") {
    HashRouter {
        QueryClientProvider {
            client = QueryClient()
            header {
                nav {
                    Link {
                        +"Список"
                        to = Config.uploadPath
                    }
                    Link {
                        +"Добавить"
                        to = "addBibTex"
                    }
                    Link {
                        +"Найти"
                        to = "findBibTex"
                    }
                    Link {
                        +"Сформировать"
                        to = "form"
                    }
                }
            }
            Routes {
                Route {
                    path = "addBibTex"
                    element = CInputFileBibtex.create()
                }
                Route {
                    path = "findBibTex"
                    element = CReadCriterion.create {
                        this.form = false
                    }
                }
                Route {
                    path = Config.uploadPath
                    element = CBibtexList.create()
                }
                Route {
                    path = Config.uploadPath + ":id"
                    element = CCurrentBibtex.create()
                }
                Route {
                    path = "form"
                    element = CReadCriterion.create {
                        this.form = true
                    }
                }
            }
        }
    }
//    footer {
//        className = ClassName("footer")
//        div {
//            +"20m application"
//        }
//    }
}