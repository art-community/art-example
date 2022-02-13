package ru.meta

import io.art.core.`property`.LazyProperty
import io.art.http.communicator.HttpCommunicationDecorator
import io.art.meta.model.InstanceMetaMethod
import io.art.meta.model.MetaClass
import io.art.meta.model.MetaConstructor
import io.art.meta.model.MetaField
import io.art.meta.model.MetaLibrary
import io.art.meta.model.MetaMethod
import io.art.meta.model.MetaPackage
import io.art.meta.model.MetaParameter
import io.art.meta.model.MetaProxy
import io.art.meta.model.MetaType.metaEnum
import io.art.meta.model.MetaType.metaType
import io.art.meta.model.StaticMetaMethod
import io.art.transport.constants.TransportModuleConstants
import io.netty.handler.codec.http.HttpHeaders
import io.netty.handler.codec.http.cookie.Cookie
import java.util.function.Function
import java.util.function.UnaryOperator
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.Throwable
import kotlin.collections.Map
import kotlin.jvm.Throws
import kotlin.sequences.Sequence
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import ru.Request
import ru.communicator.MyCommunicator
import ru.communicator.MyPortal
import ru.model.Model
import ru.service.MyService

@Suppress("warnings")
public class MetaExampleKotlin : MetaLibrary {
  private val ruPackage: MetaRuPackage = register(MetaRuPackage())

  public constructor(vararg dependencies: MetaLibrary) : super(dependencies)

  public fun ruPackage(): MetaRuPackage = ruPackage

  public class MetaRuPackage : MetaPackage {
    private val requestClass: MetaRequestClass = register(MetaRequestClass())

    private val communicatorPackage: MetaCommunicatorPackage = register(MetaCommunicatorPackage())

    private val servicePackage: MetaServicePackage = register(MetaServicePackage())

    private val modelPackage: MetaModelPackage = register(MetaModelPackage())

    internal constructor() : super("ru")

    public fun requestClass(): MetaRequestClass = requestClass

    public fun communicatorPackage(): MetaCommunicatorPackage = communicatorPackage

    public fun servicePackage(): MetaServicePackage = servicePackage

    public fun modelPackage(): MetaModelPackage = modelPackage

    public class MetaRequestClass : MetaClass<Request> {
      private val `constructor`: MetaConstructorConstructor = register(MetaConstructorConstructor())

      private val successField: MetaField<String> =
          register(MetaField("success", metaType<String>(String::class.java), false, null))

      private final val getSuccessMethod: MetaGetSuccessMethod = register(MetaGetSuccessMethod())

      internal constructor() : super(metaType<Request>(Request::class.java))

      public fun `constructor`(): MetaConstructorConstructor = constructor

      public fun successField(): MetaField<String> = successField

      public fun getSuccessMethod(): MetaGetSuccessMethod = getSuccessMethod

      public companion object {
        private final val self: LazyProperty<MetaRequestClass> = MetaClass.self(Request::class.java)

        public fun request(): MetaRequestClass = self.get()
      }

      public class MetaConstructorConstructor : MetaConstructor<Request> {
        private val successParameter: MetaParameter<String> = register(MetaParameter(0,
            "success",metaType<String>(String::class.java)))

        internal constructor() : super(metaType<Request>(Request::class.java), null)

        @Throws(Throwable::class)
        public override fun invoke(arguments: Array<Any>): Request {
          return Request(arguments[0] as String)
        }

        @Throws(Throwable::class)
        public override fun invoke(argument: Any): Request {
          return Request(argument as String)
        }

        public fun successParameter(): MetaParameter<String> = successParameter
      }

      public class MetaGetSuccessMethod : InstanceMetaMethod<Request, String> {
        internal constructor() : super("getSuccess", metaType<String>(String::class.java), null)

        @Throws(Throwable::class)
        public override fun invoke(instance: Request): Any? = instance.success

        @Throws(Throwable::class)
        public override fun invoke(instance: Request, arguments: Array<Any>): Any? =
            instance.success
      }
    }

    public class MetaCommunicatorPackage : MetaPackage {
      private val myCommunicatorClass: MetaMyCommunicatorClass = register(MetaMyCommunicatorClass())

      private val myPortalClass: MetaMyPortalClass = register(MetaMyPortalClass())

      internal constructor() : super("communicator")

      public fun myCommunicatorClass(): MetaMyCommunicatorClass = myCommunicatorClass

      public fun myPortalClass(): MetaMyPortalClass = myPortalClass

      public class MetaMyCommunicatorClass : MetaClass<MyCommunicator> {
        private final val myMethodMethod: MetaMyMethodMethod = register(MetaMyMethodMethod())

        private final val getModelMethod: MetaGetModelMethod = register(MetaGetModelMethod())

        private final val compensationMethod: MetaCompensationMethod =
            register(MetaCompensationMethod())

        private final val decorateMethod: MetaDecorateMethod = register(MetaDecorateMethod())

        private final val useGetMethod: MetaUseGetMethod = register(MetaUseGetMethod())

        private final val usePostMethod: MetaUsePostMethod = register(MetaUsePostMethod())

        private final val usePutMethod: MetaUsePutMethod = register(MetaUsePutMethod())

        private final val usePatchMethod: MetaUsePatchMethod = register(MetaUsePatchMethod())

        private final val useOptionsMethod: MetaUseOptionsMethod = register(MetaUseOptionsMethod())

        private final val useHeadMethod: MetaUseHeadMethod = register(MetaUseHeadMethod())

        private final val useWsMethod: MetaUseWsMethod = register(MetaUseWsMethod())

        private final val pathParameterMethod: MetaPathParameterMethod =
            register(MetaPathParameterMethod())

        private final val queryParameterMethod: MetaQueryParameterMethod =
            register(MetaQueryParameterMethod())

        private final val headersMethod: MetaHeadersMethod = register(MetaHeadersMethod())

        private final val clientMethod: MetaClientMethod = register(MetaClientMethod())

        private final val uriMethod: MetaUriMethod = register(MetaUriMethod())

        private final val uri_1Method: MetaUri_1Method = register(MetaUri_1Method())

        private final val inputMethod: MetaInputMethod = register(MetaInputMethod())

        private final val outputMethod: MetaOutputMethod = register(MetaOutputMethod())

        private final val cookieMethod: MetaCookieMethod = register(MetaCookieMethod())

        internal constructor() : super(metaType<MyCommunicator>(MyCommunicator::class.java))

        public fun myMethodMethod(): MetaMyMethodMethod = myMethodMethod

        public fun getModelMethod(): MetaGetModelMethod = getModelMethod

        public fun compensationMethod(): MetaCompensationMethod = compensationMethod

        public fun decorateMethod(): MetaDecorateMethod = decorateMethod

        public fun useGetMethod(): MetaUseGetMethod = useGetMethod

        public fun usePostMethod(): MetaUsePostMethod = usePostMethod

        public fun usePutMethod(): MetaUsePutMethod = usePutMethod

        public fun usePatchMethod(): MetaUsePatchMethod = usePatchMethod

        public fun useOptionsMethod(): MetaUseOptionsMethod = useOptionsMethod

        public fun useHeadMethod(): MetaUseHeadMethod = useHeadMethod

        public fun useWsMethod(): MetaUseWsMethod = useWsMethod

        public fun pathParameterMethod(): MetaPathParameterMethod = pathParameterMethod

        public fun queryParameterMethod(): MetaQueryParameterMethod = queryParameterMethod

        public fun headersMethod(): MetaHeadersMethod = headersMethod

        public fun clientMethod(): MetaClientMethod = clientMethod

        public fun uriMethod(): MetaUriMethod = uriMethod

        public fun uri_1Method(): MetaUri_1Method = uri_1Method

        public fun inputMethod(): MetaInputMethod = inputMethod

        public fun outputMethod(): MetaOutputMethod = outputMethod

        public fun cookieMethod(): MetaCookieMethod = cookieMethod

        public override fun proxy(invocations: Map<MetaMethod<*>, Function<Any?, Any?>>): MetaProxy
            = MetaMyCommunicatorProxy(invocations)

        public companion object {
          private final val self: LazyProperty<MetaMyCommunicatorClass> =
              MetaClass.self(MyCommunicator::class.java)

          public fun myCommunicator(): MetaMyCommunicatorClass = self.get()
        }

        public class MetaMyMethodMethod : InstanceMetaMethod<MyCommunicator, Model> {
          private val modelParameter: MetaParameter<Model> = register(MetaParameter(0,
              "model",metaType<Model>(Model::class.java)))

          internal constructor() : super("myMethod", metaType<Model>(Model::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.myMethod(arguments[0] as Model)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.myMethod(argument as Model)
          }

          public fun modelParameter(): MetaParameter<Model> = modelParameter
        }

        public class MetaGetModelMethod : InstanceMetaMethod<MyCommunicator, Model> {
          internal constructor() : super("getModel", metaType<Model>(Model::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.getModel()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.getModel()
          }
        }

        public class MetaCompensationMethod : InstanceMetaMethod<MyCommunicator, Mono<String>> {
          private val inputParameter: MetaParameter<Flux<String>> = register(MetaParameter(0,
              "input",metaType<Flux<String>>(Flux::class.java,metaType<String>(String::class.java))))

          internal constructor() :
              super("compensation", metaType<Mono<String>>(Mono::class.java,metaType<String>(String::class.java)), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.compensation(arguments[0] as Flux<String>)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.compensation(argument as Flux<String>)
          }

          public fun inputParameter(): MetaParameter<Flux<String>> = inputParameter
        }

        public class MetaDecorateMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpCommunicationDecorator>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpCommunicationDecorator>>(UnaryOperator::class.java,metaType<HttpCommunicationDecorator>(HttpCommunicationDecorator::class.java))))

          internal constructor() :
              super("decorate", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.decorate(arguments[0] as UnaryOperator<HttpCommunicationDecorator>)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.decorate(argument as UnaryOperator<HttpCommunicationDecorator>)
          }

          public fun p0Parameter(): MetaParameter<UnaryOperator<HttpCommunicationDecorator>> =
              p0Parameter
        }

        public class MetaUseGetMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          internal constructor() :
              super("useGet", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.useGet()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.useGet()
          }
        }

        public class MetaUsePostMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          internal constructor() :
              super("usePost", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.usePost()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.usePost()
          }
        }

        public class MetaUsePutMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          internal constructor() :
              super("usePut", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.usePut()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.usePut()
          }
        }

        public class MetaUsePatchMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          internal constructor() :
              super("usePatch", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.usePatch()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.usePatch()
          }
        }

        public class MetaUseOptionsMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          internal constructor() :
              super("useOptions", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.useOptions()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.useOptions()
          }
        }

        public class MetaUseHeadMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          internal constructor() :
              super("useHead", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.useHead()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.useHead()
          }
        }

        public class MetaUseWsMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          internal constructor() :
              super("useWs", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.useWs()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.useWs()
          }
        }

        public class MetaPathParameterMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          internal constructor() :
              super("pathParameter", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.pathParameter(arguments[0] as String)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.pathParameter(argument as String)
          }

          public fun p0Parameter(): MetaParameter<String> = p0Parameter
        }

        public class MetaQueryParameterMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          private val p1Parameter: MetaParameter<String> = register(MetaParameter(1,
              "p1",metaType<String>(String::class.java)))

          internal constructor() :
              super("queryParameter", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.queryParameter(arguments[0] as String,arguments[1] as String)
          }

          public fun p0Parameter(): MetaParameter<String> = p0Parameter

          public fun p1Parameter(): MetaParameter<String> = p1Parameter
        }

        public class MetaHeadersMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpHeaders>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpHeaders>>(UnaryOperator::class.java,metaType<HttpHeaders>(HttpHeaders::class.java))))

          internal constructor() :
              super("headers", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.headers(arguments[0] as UnaryOperator<HttpHeaders>)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.headers(argument as UnaryOperator<HttpHeaders>)
          }

          public fun p0Parameter(): MetaParameter<UnaryOperator<HttpHeaders>> = p0Parameter
        }

        public class MetaClientMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpClient>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpClient>>(UnaryOperator::class.java,metaType<HttpClient>(HttpClient::class.java))))

          internal constructor() :
              super("client", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.client(arguments[0] as UnaryOperator<HttpClient>)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.client(argument as UnaryOperator<HttpClient>)
          }

          public fun p0Parameter(): MetaParameter<UnaryOperator<HttpClient>> = p0Parameter
        }

        public class MetaUriMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          internal constructor() : super("uri", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.uri(arguments[0] as String)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.uri(argument as String)
          }

          public fun p0Parameter(): MetaParameter<String> = p0Parameter
        }

        public class MetaUri_1Method : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<String>> = register(MetaParameter(0,
              "p0",metaType<UnaryOperator<String>>(UnaryOperator::class.java,metaType<String>(String::class.java))))

          internal constructor() : super("uri", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.uri(arguments[0] as UnaryOperator<String>)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.uri(argument as UnaryOperator<String>)
          }

          public fun p0Parameter(): MetaParameter<UnaryOperator<String>> = p0Parameter
        }

        public class MetaInputMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<TransportModuleConstants.DataFormat> =
              register(MetaParameter(0,
              "p0",metaEnum(TransportModuleConstants.DataFormat::class.java,
              TransportModuleConstants.DataFormat::valueOf)))

          internal constructor() :
              super("input", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.input(arguments[0] as TransportModuleConstants.DataFormat)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.input(argument as TransportModuleConstants.DataFormat)
          }

          public fun p0Parameter(): MetaParameter<TransportModuleConstants.DataFormat> = p0Parameter
        }

        public class MetaOutputMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<TransportModuleConstants.DataFormat> =
              register(MetaParameter(0,
              "p0",metaEnum(TransportModuleConstants.DataFormat::class.java,
              TransportModuleConstants.DataFormat::valueOf)))

          internal constructor() :
              super("output", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.output(arguments[0] as TransportModuleConstants.DataFormat)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.output(argument as TransportModuleConstants.DataFormat)
          }

          public fun p0Parameter(): MetaParameter<TransportModuleConstants.DataFormat> = p0Parameter
        }

        public class MetaCookieMethod : InstanceMetaMethod<MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          private val p1Parameter: MetaParameter<Cookie> = register(MetaParameter(1,
              "p1",metaType<Cookie>(Cookie::class.java)))

          internal constructor() :
              super("cookie", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.cookie(arguments[0] as String,arguments[1] as Cookie)
          }

          public fun p0Parameter(): MetaParameter<String> = p0Parameter

          public fun p1Parameter(): MetaParameter<Cookie> = p1Parameter
        }

        public inner class MetaMyCommunicatorProxy : MetaProxy, MyCommunicator {
          private final val myMethodInvocation: Function<Any?, Any?>

          private final val getModelInvocation: Function<Any?, Any?>

          private final val compensationInvocation: Function<Any?, Any?>

          public constructor(invocations: Map<MetaMethod<*>, Function<Any?, Any?>>) :
              super(invocations) {
            myMethodInvocation = invocations[myMethodMethod]!!
            getModelInvocation = invocations[getModelMethod]!!
            compensationInvocation = invocations[compensationMethod]!!
          }

          public override fun myMethod(model: Model): Model = myMethodInvocation.apply(model) as
              Model

          public override fun getModel(): Model = getModelInvocation.apply(null) as Model

          public override fun compensation(input: Flux<String>): Mono<String> =
              compensationInvocation.apply(input) as Mono<String>
        }
      }

      public class MetaMyPortalClass : MetaClass<MyPortal> {
        private final val myMethod: MetaMyMethod = register(MetaMyMethod())

        internal constructor() : super(metaType<MyPortal>(MyPortal::class.java))

        public fun myMethod(): MetaMyMethod = myMethod

        public override fun proxy(invocations: Map<MetaMethod<*>, Function<Any?, Any?>>): MetaProxy
            = MetaMyPortalProxy(invocations)

        public companion object {
          private final val self: LazyProperty<MetaMyPortalClass> =
              MetaClass.self(MyPortal::class.java)

          public fun myPortal(): MetaMyPortalClass = self.get()
        }

        public class MetaMyMethod : InstanceMetaMethod<MyPortal, MyCommunicator> {
          internal constructor() : super("my", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyPortal, arguments: Array<Any>): Any? {
            return instance.my()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyPortal): Any? {
            return instance.my()
          }
        }

        public inner class MetaMyPortalProxy : MetaProxy, MyPortal {
          private final val myInvocation: Function<Any?, Any?>

          public constructor(invocations: Map<MetaMethod<*>, Function<Any?, Any?>>) :
              super(invocations) {
            myInvocation = invocations[myMethod]!!
          }

          public override fun my(): MyCommunicator = myInvocation.apply(null) as MyCommunicator
        }
      }
    }

    public class MetaServicePackage : MetaPackage {
      private val myServiceClass: MetaMyServiceClass = register(MetaMyServiceClass())

      internal constructor() : super("service")

      public fun myServiceClass(): MetaMyServiceClass = myServiceClass

      public class MetaMyServiceClass : MetaClass<MyService> {
        private final val myMethodMethod: MetaMyMethodMethod = register(MetaMyMethodMethod())

        private final val getModelMethod: MetaGetModelMethod = register(MetaGetModelMethod())

        private final val compensationMethod: MetaCompensationMethod =
            register(MetaCompensationMethod())

        private final val decorateMethod: MetaDecorateMethod = register(MetaDecorateMethod())

        private final val useGetMethod: MetaUseGetMethod = register(MetaUseGetMethod())

        private final val usePostMethod: MetaUsePostMethod = register(MetaUsePostMethod())

        private final val usePutMethod: MetaUsePutMethod = register(MetaUsePutMethod())

        private final val usePatchMethod: MetaUsePatchMethod = register(MetaUsePatchMethod())

        private final val useOptionsMethod: MetaUseOptionsMethod = register(MetaUseOptionsMethod())

        private final val useHeadMethod: MetaUseHeadMethod = register(MetaUseHeadMethod())

        private final val useWsMethod: MetaUseWsMethod = register(MetaUseWsMethod())

        private final val pathParameterMethod: MetaPathParameterMethod =
            register(MetaPathParameterMethod())

        private final val queryParameterMethod: MetaQueryParameterMethod =
            register(MetaQueryParameterMethod())

        private final val headersMethod: MetaHeadersMethod = register(MetaHeadersMethod())

        private final val clientMethod: MetaClientMethod = register(MetaClientMethod())

        private final val uriMethod: MetaUriMethod = register(MetaUriMethod())

        private final val uri_1Method: MetaUri_1Method = register(MetaUri_1Method())

        private final val inputMethod: MetaInputMethod = register(MetaInputMethod())

        private final val outputMethod: MetaOutputMethod = register(MetaOutputMethod())

        private final val cookieMethod: MetaCookieMethod = register(MetaCookieMethod())

        internal constructor() : super(metaType<MyService>(MyService::class.java))

        public fun myMethodMethod(): MetaMyMethodMethod = myMethodMethod

        public fun getModelMethod(): MetaGetModelMethod = getModelMethod

        public fun compensationMethod(): MetaCompensationMethod = compensationMethod

        public fun decorateMethod(): MetaDecorateMethod = decorateMethod

        public fun useGetMethod(): MetaUseGetMethod = useGetMethod

        public fun usePostMethod(): MetaUsePostMethod = usePostMethod

        public fun usePutMethod(): MetaUsePutMethod = usePutMethod

        public fun usePatchMethod(): MetaUsePatchMethod = usePatchMethod

        public fun useOptionsMethod(): MetaUseOptionsMethod = useOptionsMethod

        public fun useHeadMethod(): MetaUseHeadMethod = useHeadMethod

        public fun useWsMethod(): MetaUseWsMethod = useWsMethod

        public fun pathParameterMethod(): MetaPathParameterMethod = pathParameterMethod

        public fun queryParameterMethod(): MetaQueryParameterMethod = queryParameterMethod

        public fun headersMethod(): MetaHeadersMethod = headersMethod

        public fun clientMethod(): MetaClientMethod = clientMethod

        public fun uriMethod(): MetaUriMethod = uriMethod

        public fun uri_1Method(): MetaUri_1Method = uri_1Method

        public fun inputMethod(): MetaInputMethod = inputMethod

        public fun outputMethod(): MetaOutputMethod = outputMethod

        public fun cookieMethod(): MetaCookieMethod = cookieMethod

        public companion object {
          private final val self: LazyProperty<MetaMyServiceClass> =
              MetaClass.self(MyService::class.java)

          public fun myService(): MetaMyServiceClass = self.get()
        }

        public class MetaMyMethodMethod : StaticMetaMethod<Model> {
          private val modelParameter: MetaParameter<Model> = register(MetaParameter(0,
              "model",metaType<Model>(Model::class.java)))

          internal constructor() : super("myMethod", metaType<Model>(Model::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.myMethod(arguments[0] as Model)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.myMethod(argument as Model)
          }

          public fun modelParameter(): MetaParameter<Model> = modelParameter
        }

        public class MetaGetModelMethod : StaticMetaMethod<Model> {
          internal constructor() : super("getModel", metaType<Model>(Model::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.getModel()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.getModel()
          }
        }

        public class MetaCompensationMethod : StaticMetaMethod<Mono<String>> {
          private val inputParameter: MetaParameter<Flux<String>> = register(MetaParameter(0,
              "input",metaType<Flux<String>>(Flux::class.java,metaType<String>(String::class.java))))

          internal constructor() :
              super("compensation", metaType<Mono<String>>(Mono::class.java,metaType<String>(String::class.java)), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.compensation(arguments[0] as Flux<String>)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.compensation(argument as Flux<String>)
          }

          public fun inputParameter(): MetaParameter<Flux<String>> = inputParameter
        }

        public class MetaDecorateMethod : StaticMetaMethod<MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpCommunicationDecorator>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpCommunicationDecorator>>(UnaryOperator::class.java,metaType<HttpCommunicationDecorator>(HttpCommunicationDecorator::class.java))))

          internal constructor() :
              super("decorate", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.decorate(arguments[0] as UnaryOperator<HttpCommunicationDecorator>)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.decorate(argument as UnaryOperator<HttpCommunicationDecorator>)
          }

          public fun p0Parameter(): MetaParameter<UnaryOperator<HttpCommunicationDecorator>> =
              p0Parameter
        }

        public class MetaUseGetMethod : StaticMetaMethod<MyCommunicator> {
          internal constructor() :
              super("useGet", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.useGet()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.useGet()
          }
        }

        public class MetaUsePostMethod : StaticMetaMethod<MyCommunicator> {
          internal constructor() :
              super("usePost", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.usePost()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.usePost()
          }
        }

        public class MetaUsePutMethod : StaticMetaMethod<MyCommunicator> {
          internal constructor() :
              super("usePut", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.usePut()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.usePut()
          }
        }

        public class MetaUsePatchMethod : StaticMetaMethod<MyCommunicator> {
          internal constructor() :
              super("usePatch", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.usePatch()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.usePatch()
          }
        }

        public class MetaUseOptionsMethod : StaticMetaMethod<MyCommunicator> {
          internal constructor() :
              super("useOptions", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.useOptions()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.useOptions()
          }
        }

        public class MetaUseHeadMethod : StaticMetaMethod<MyCommunicator> {
          internal constructor() :
              super("useHead", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.useHead()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.useHead()
          }
        }

        public class MetaUseWsMethod : StaticMetaMethod<MyCommunicator> {
          internal constructor() :
              super("useWs", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.useWs()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.useWs()
          }
        }

        public class MetaPathParameterMethod : StaticMetaMethod<MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          internal constructor() :
              super("pathParameter", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.pathParameter(arguments[0] as String)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.pathParameter(argument as String)
          }

          public fun p0Parameter(): MetaParameter<String> = p0Parameter
        }

        public class MetaQueryParameterMethod : StaticMetaMethod<MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          private val p1Parameter: MetaParameter<String> = register(MetaParameter(1,
              "p1",metaType<String>(String::class.java)))

          internal constructor() :
              super("queryParameter", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.queryParameter(arguments[0] as String,arguments[1] as String)
          }

          public fun p0Parameter(): MetaParameter<String> = p0Parameter

          public fun p1Parameter(): MetaParameter<String> = p1Parameter
        }

        public class MetaHeadersMethod : StaticMetaMethod<MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpHeaders>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpHeaders>>(UnaryOperator::class.java,metaType<HttpHeaders>(HttpHeaders::class.java))))

          internal constructor() :
              super("headers", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.headers(arguments[0] as UnaryOperator<HttpHeaders>)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.headers(argument as UnaryOperator<HttpHeaders>)
          }

          public fun p0Parameter(): MetaParameter<UnaryOperator<HttpHeaders>> = p0Parameter
        }

        public class MetaClientMethod : StaticMetaMethod<MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpClient>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpClient>>(UnaryOperator::class.java,metaType<HttpClient>(HttpClient::class.java))))

          internal constructor() :
              super("client", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.client(arguments[0] as UnaryOperator<HttpClient>)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.client(argument as UnaryOperator<HttpClient>)
          }

          public fun p0Parameter(): MetaParameter<UnaryOperator<HttpClient>> = p0Parameter
        }

        public class MetaUriMethod : StaticMetaMethod<MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          internal constructor() : super("uri", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.uri(arguments[0] as String)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.uri(argument as String)
          }

          public fun p0Parameter(): MetaParameter<String> = p0Parameter
        }

        public class MetaUri_1Method : StaticMetaMethod<MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<String>> = register(MetaParameter(0,
              "p0",metaType<UnaryOperator<String>>(UnaryOperator::class.java,metaType<String>(String::class.java))))

          internal constructor() : super("uri", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.uri(arguments[0] as UnaryOperator<String>)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.uri(argument as UnaryOperator<String>)
          }

          public fun p0Parameter(): MetaParameter<UnaryOperator<String>> = p0Parameter
        }

        public class MetaInputMethod : StaticMetaMethod<MyCommunicator> {
          private val p0Parameter: MetaParameter<TransportModuleConstants.DataFormat> =
              register(MetaParameter(0,
              "p0",metaEnum(TransportModuleConstants.DataFormat::class.java,
              TransportModuleConstants.DataFormat::valueOf)))

          internal constructor() :
              super("input", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.input(arguments[0] as TransportModuleConstants.DataFormat)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.input(argument as TransportModuleConstants.DataFormat)
          }

          public fun p0Parameter(): MetaParameter<TransportModuleConstants.DataFormat> = p0Parameter
        }

        public class MetaOutputMethod : StaticMetaMethod<MyCommunicator> {
          private val p0Parameter: MetaParameter<TransportModuleConstants.DataFormat> =
              register(MetaParameter(0,
              "p0",metaEnum(TransportModuleConstants.DataFormat::class.java,
              TransportModuleConstants.DataFormat::valueOf)))

          internal constructor() :
              super("output", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.output(arguments[0] as TransportModuleConstants.DataFormat)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.output(argument as TransportModuleConstants.DataFormat)
          }

          public fun p0Parameter(): MetaParameter<TransportModuleConstants.DataFormat> = p0Parameter
        }

        public class MetaCookieMethod : StaticMetaMethod<MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          private val p1Parameter: MetaParameter<Cookie> = register(MetaParameter(1,
              "p1",metaType<Cookie>(Cookie::class.java)))

          internal constructor() :
              super("cookie", metaType<MyCommunicator>(MyCommunicator::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.cookie(arguments[0] as String,arguments[1] as Cookie)
          }

          public fun p0Parameter(): MetaParameter<String> = p0Parameter

          public fun p1Parameter(): MetaParameter<Cookie> = p1Parameter
        }
      }
    }

    public class MetaModelPackage : MetaPackage {
      private val modelClass: MetaModelClass = register(MetaModelClass())

      internal constructor() : super("model")

      public fun modelClass(): MetaModelClass = modelClass

      public class MetaModelClass : MetaClass<Model> {
        private val `constructor`: MetaConstructorConstructor =
            register(MetaConstructorConstructor())

        private val valueField: MetaField<Sequence<String>> =
            register(MetaField("value", metaType<Sequence<String>>(Sequence::class.java,metaType<String>(String::class.java)), false, null))

        private final val getValueMethod: MetaGetValueMethod = register(MetaGetValueMethod())

        internal constructor() : super(metaType<Model>(Model::class.java))

        public fun `constructor`(): MetaConstructorConstructor = constructor

        public fun valueField(): MetaField<Sequence<String>> = valueField

        public fun getValueMethod(): MetaGetValueMethod = getValueMethod

        public companion object {
          private final val self: LazyProperty<MetaModelClass> = MetaClass.self(Model::class.java)

          public fun model(): MetaModelClass = self.get()
        }

        public class MetaConstructorConstructor : MetaConstructor<Model> {
          private val valueParameter: MetaParameter<Sequence<String>> = register(MetaParameter(0,
              "value",metaType<Sequence<String>>(Sequence::class.java,metaType<String>(String::class.java))))

          internal constructor() : super(metaType<Model>(Model::class.java), null)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Model {
            return Model(arguments[0] as Sequence<String>)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Model {
            return Model(argument as Sequence<String>)
          }

          public fun valueParameter(): MetaParameter<Sequence<String>> = valueParameter
        }

        public class MetaGetValueMethod : InstanceMetaMethod<Model, Sequence<String>> {
          internal constructor() :
              super("getValue", metaType<Sequence<String>>(Sequence::class.java,metaType<String>(String::class.java)), null)

          @Throws(Throwable::class)
          public override fun invoke(instance: Model): Any? = instance.`value`

          @Throws(Throwable::class)
          public override fun invoke(instance: Model, arguments: Array<Any>): Any? =
              instance.`value`
        }
      }
    }
  }
}
