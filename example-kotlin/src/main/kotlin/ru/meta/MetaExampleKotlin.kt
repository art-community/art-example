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
import io.art.meta.model.MetaType.metaArray
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
      private val `constructor`: MetaConstructorConstructor =
          register(MetaConstructorConstructor(this))

      private val successField: MetaField<MetaRequestClass, String> =
          register(MetaField("success",metaType<String>(String::class.java),false,this))

      private final val getSuccessMethod: MetaGetSuccessMethod =
          register(MetaGetSuccessMethod(this))

      internal constructor() : super(metaType<Request>(Request::class.java))

      public fun `constructor`(): MetaConstructorConstructor = constructor

      public fun successField(): MetaField<MetaRequestClass, String> = successField

      public fun getSuccessMethod(): MetaGetSuccessMethod = getSuccessMethod

      public companion object {
        private final val self: LazyProperty<MetaRequestClass> = MetaClass.self(Request::class.java)

        public fun request(): MetaRequestClass = self.get()
      }

      public class MetaConstructorConstructor : MetaConstructor<MetaRequestClass, Request> {
        private val successParameter: MetaParameter<String> = register(MetaParameter(0,
            "success",metaType<String>(String::class.java)))

        internal constructor(owner: MetaRequestClass) :
            super(metaType<Request>(Request::class.java),owner)

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

      public class MetaGetSuccessMethod : InstanceMetaMethod<MetaRequestClass, Request, String> {
        internal constructor(owner: MetaRequestClass) :
            super("getSuccess",metaType<String>(String::class.java),owner)

        @Throws(Throwable::class)
        public override fun invoke(instance: Request): Any? = instance.success

        @Throws(Throwable::class)
        public override fun invoke(instance: Request, arguments: Array<Any>): Any? =
            instance.success
      }
    }

    public class MetaCommunicatorPackage : MetaPackage {
      private val myCommunicatorClass: MetaMyCommunicatorClass = register(MetaMyCommunicatorClass())

      internal constructor() : super("communicator")

      public fun myCommunicatorClass(): MetaMyCommunicatorClass = myCommunicatorClass

      public class MetaMyCommunicatorClass : MetaClass<MyCommunicator> {
        private final val myMethodMethod: MetaMyMethodMethod = register(MetaMyMethodMethod(this))

        private final val getModelMethod: MetaGetModelMethod = register(MetaGetModelMethod(this))

        private final val compensationMethod: MetaCompensationMethod =
            register(MetaCompensationMethod(this))

        private final val decorateMethod: MetaDecorateMethod = register(MetaDecorateMethod(this))

        private final val useGetMethod: MetaUseGetMethod = register(MetaUseGetMethod(this))

        private final val usePostMethod: MetaUsePostMethod = register(MetaUsePostMethod(this))

        private final val usePutMethod: MetaUsePutMethod = register(MetaUsePutMethod(this))

        private final val usePatchMethod: MetaUsePatchMethod = register(MetaUsePatchMethod(this))

        private final val useOptionsMethod: MetaUseOptionsMethod =
            register(MetaUseOptionsMethod(this))

        private final val useHeadMethod: MetaUseHeadMethod = register(MetaUseHeadMethod(this))

        private final val useWsMethod: MetaUseWsMethod = register(MetaUseWsMethod(this))

        private final val pathParameterMethod: MetaPathParameterMethod =
            register(MetaPathParameterMethod(this))

        private final val queryParameterMethod: MetaQueryParameterMethod =
            register(MetaQueryParameterMethod(this))

        private final val headersMethod: MetaHeadersMethod = register(MetaHeadersMethod(this))

        private final val clientMethod: MetaClientMethod = register(MetaClientMethod(this))

        private final val uriMethod: MetaUriMethod = register(MetaUriMethod(this))

        private final val uri_1Method: MetaUri_1Method = register(MetaUri_1Method(this))

        private final val inputMethod: MetaInputMethod = register(MetaInputMethod(this))

        private final val outputMethod: MetaOutputMethod = register(MetaOutputMethod(this))

        private final val cookieMethod: MetaCookieMethod = register(MetaCookieMethod(this))

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

        public override
            fun proxy(invocations: Map<MetaMethod<MetaClass<*>, *>, Function<Any?, Any?>>):
            MetaProxy = MetaMyCommunicatorProxy(invocations)

        public companion object {
          private final val self: LazyProperty<MetaMyCommunicatorClass> =
              MetaClass.self(MyCommunicator::class.java)

          public fun myCommunicator(): MetaMyCommunicatorClass = self.get()
        }

        public class MetaMyMethodMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, Model> {
          private val modelParameter: MetaParameter<Model> = register(MetaParameter(0,
              "model",metaType<Model>(Model::class.java)))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("myMethod",metaType<Model>(Model::class.java),owner)

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

        public class MetaGetModelMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, Model> {
          internal constructor(owner: MetaMyCommunicatorClass) :
              super("getModel",metaType<Model>(Model::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.getModel()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.getModel()
          }
        }

        public class MetaCompensationMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, Mono<String>> {
          private val inputParameter: MetaParameter<Flux<String>> = register(MetaParameter(0,
              "input",metaType<Flux<String>>(Flux::class.java,metaType<String>(String::class.java))))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("compensation",metaType<Mono<String>>(Mono::class.java,metaType<String>(String::class.java)),owner)

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

        public class MetaDecorateMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpCommunicationDecorator>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpCommunicationDecorator>>(UnaryOperator::class.java,metaType<HttpCommunicationDecorator>(HttpCommunicationDecorator::class.java))))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("decorate",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaUseGetMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          internal constructor(owner: MetaMyCommunicatorClass) :
              super("useGet",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.useGet()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.useGet()
          }
        }

        public class MetaUsePostMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          internal constructor(owner: MetaMyCommunicatorClass) :
              super("usePost",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.usePost()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.usePost()
          }
        }

        public class MetaUsePutMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          internal constructor(owner: MetaMyCommunicatorClass) :
              super("usePut",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.usePut()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.usePut()
          }
        }

        public class MetaUsePatchMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          internal constructor(owner: MetaMyCommunicatorClass) :
              super("usePatch",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.usePatch()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.usePatch()
          }
        }

        public class MetaUseOptionsMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          internal constructor(owner: MetaMyCommunicatorClass) :
              super("useOptions",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.useOptions()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.useOptions()
          }
        }

        public class MetaUseHeadMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          internal constructor(owner: MetaMyCommunicatorClass) :
              super("useHead",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.useHead()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.useHead()
          }
        }

        public class MetaUseWsMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          internal constructor(owner: MetaMyCommunicatorClass) :
              super("useWs",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.useWs()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.useWs()
          }
        }

        public class MetaPathParameterMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("pathParameter",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaQueryParameterMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          private val p1Parameter: MetaParameter<String> = register(MetaParameter(1,
              "p1",metaType<String>(String::class.java)))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("queryParameter",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.queryParameter(arguments[0] as String,arguments[1] as String)
          }

          public fun p0Parameter(): MetaParameter<String> = p0Parameter

          public fun p1Parameter(): MetaParameter<String> = p1Parameter
        }

        public class MetaHeadersMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpHeaders>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpHeaders>>(UnaryOperator::class.java,metaType<HttpHeaders>(HttpHeaders::class.java))))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("headers",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaClientMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpClient>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpClient>>(UnaryOperator::class.java,metaType<HttpClient>(HttpClient::class.java))))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("client",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaUriMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("uri",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaUri_1Method :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<String>> = register(MetaParameter(0,
              "p0",metaType<UnaryOperator<String>>(UnaryOperator::class.java,metaType<String>(String::class.java))))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("uri",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaInputMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<TransportModuleConstants.DataFormat> =
              register(MetaParameter(0,
              "p0",metaEnum(TransportModuleConstants.DataFormat::class.java,
              TransportModuleConstants.DataFormat::valueOf)))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("input",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaOutputMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<TransportModuleConstants.DataFormat> =
              register(MetaParameter(0,
              "p0",metaEnum(TransportModuleConstants.DataFormat::class.java,
              TransportModuleConstants.DataFormat::valueOf)))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("output",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaCookieMethod :
            InstanceMetaMethod<MetaMyCommunicatorClass, MyCommunicator, MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          private val p1Parameter: MetaParameter<Cookie> = register(MetaParameter(1,
              "p1",metaType<Cookie>(Cookie::class.java)))

          internal constructor(owner: MetaMyCommunicatorClass) :
              super("cookie",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

          public constructor(invocations: Map<MetaMethod<MetaClass<*>, *>, Function<Any?, Any?>>) :
              super(invocations) {
            myMethodInvocation = invocations[myMethodMethod as MetaMethod<MetaClass<*>, *>]!!
            getModelInvocation = invocations[getModelMethod as MetaMethod<MetaClass<*>, *>]!!
            compensationInvocation = invocations[compensationMethod as
                MetaMethod<MetaClass<*>, *>]!!
          }

          public override fun myMethod(model: Model): Model = myMethodInvocation.apply(model) as
              Model

          public override fun getModel(): Model = getModelInvocation.apply(null) as Model

          public override fun compensation(input: Flux<String>): Mono<String> =
              compensationInvocation.apply(input) as Mono<String>
        }
      }
    }

    public class MetaServicePackage : MetaPackage {
      private val myServiceClass: MetaMyServiceClass = register(MetaMyServiceClass())

      internal constructor() : super("service")

      public fun myServiceClass(): MetaMyServiceClass = myServiceClass

      public class MetaMyServiceClass : MetaClass<MyService> {
        private final val myMethodMethod: MetaMyMethodMethod = register(MetaMyMethodMethod(this))

        private final val getModelMethod: MetaGetModelMethod = register(MetaGetModelMethod(this))

        private final val compensationMethod: MetaCompensationMethod =
            register(MetaCompensationMethod(this))

        private final val decorateMethod: MetaDecorateMethod = register(MetaDecorateMethod(this))

        private final val useGetMethod: MetaUseGetMethod = register(MetaUseGetMethod(this))

        private final val usePostMethod: MetaUsePostMethod = register(MetaUsePostMethod(this))

        private final val usePutMethod: MetaUsePutMethod = register(MetaUsePutMethod(this))

        private final val usePatchMethod: MetaUsePatchMethod = register(MetaUsePatchMethod(this))

        private final val useOptionsMethod: MetaUseOptionsMethod =
            register(MetaUseOptionsMethod(this))

        private final val useHeadMethod: MetaUseHeadMethod = register(MetaUseHeadMethod(this))

        private final val useWsMethod: MetaUseWsMethod = register(MetaUseWsMethod(this))

        private final val pathParameterMethod: MetaPathParameterMethod =
            register(MetaPathParameterMethod(this))

        private final val queryParameterMethod: MetaQueryParameterMethod =
            register(MetaQueryParameterMethod(this))

        private final val headersMethod: MetaHeadersMethod = register(MetaHeadersMethod(this))

        private final val clientMethod: MetaClientMethod = register(MetaClientMethod(this))

        private final val uriMethod: MetaUriMethod = register(MetaUriMethod(this))

        private final val uri_1Method: MetaUri_1Method = register(MetaUri_1Method(this))

        private final val inputMethod: MetaInputMethod = register(MetaInputMethod(this))

        private final val outputMethod: MetaOutputMethod = register(MetaOutputMethod(this))

        private final val cookieMethod: MetaCookieMethod = register(MetaCookieMethod(this))

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

        public class MetaMyMethodMethod : StaticMetaMethod<MetaMyServiceClass, Model> {
          private val modelParameter: MetaParameter<Model> = register(MetaParameter(0,
              "model",metaType<Model>(Model::class.java)))

          internal constructor(owner: MetaMyServiceClass) :
              super("myMethod",metaType<Model>(Model::class.java),owner)

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

        public class MetaGetModelMethod : StaticMetaMethod<MetaMyServiceClass, Model> {
          internal constructor(owner: MetaMyServiceClass) :
              super("getModel",metaType<Model>(Model::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.getModel()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.getModel()
          }
        }

        public class MetaCompensationMethod : StaticMetaMethod<MetaMyServiceClass, Mono<String>> {
          private val inputParameter: MetaParameter<Flux<String>> = register(MetaParameter(0,
              "input",metaType<Flux<String>>(Flux::class.java,metaType<String>(String::class.java))))

          internal constructor(owner: MetaMyServiceClass) :
              super("compensation",metaType<Mono<String>>(Mono::class.java,metaType<String>(String::class.java)),owner)

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

        public class MetaDecorateMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpCommunicationDecorator>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpCommunicationDecorator>>(UnaryOperator::class.java,metaType<HttpCommunicationDecorator>(HttpCommunicationDecorator::class.java))))

          internal constructor(owner: MetaMyServiceClass) :
              super("decorate",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaUseGetMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          internal constructor(owner: MetaMyServiceClass) :
              super("useGet",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.useGet()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.useGet()
          }
        }

        public class MetaUsePostMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          internal constructor(owner: MetaMyServiceClass) :
              super("usePost",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.usePost()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.usePost()
          }
        }

        public class MetaUsePutMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          internal constructor(owner: MetaMyServiceClass) :
              super("usePut",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.usePut()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.usePut()
          }
        }

        public class MetaUsePatchMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          internal constructor(owner: MetaMyServiceClass) :
              super("usePatch",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.usePatch()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.usePatch()
          }
        }

        public class MetaUseOptionsMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          internal constructor(owner: MetaMyServiceClass) :
              super("useOptions",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.useOptions()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.useOptions()
          }
        }

        public class MetaUseHeadMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          internal constructor(owner: MetaMyServiceClass) :
              super("useHead",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.useHead()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.useHead()
          }
        }

        public class MetaUseWsMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          internal constructor(owner: MetaMyServiceClass) :
              super("useWs",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.useWs()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.useWs()
          }
        }

        public class MetaPathParameterMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator>
            {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          internal constructor(owner: MetaMyServiceClass) :
              super("pathParameter",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaQueryParameterMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator>
            {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          private val p1Parameter: MetaParameter<String> = register(MetaParameter(1,
              "p1",metaType<String>(String::class.java)))

          internal constructor(owner: MetaMyServiceClass) :
              super("queryParameter",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.queryParameter(arguments[0] as String,arguments[1] as String)
          }

          public fun p0Parameter(): MetaParameter<String> = p0Parameter

          public fun p1Parameter(): MetaParameter<String> = p1Parameter
        }

        public class MetaHeadersMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpHeaders>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpHeaders>>(UnaryOperator::class.java,metaType<HttpHeaders>(HttpHeaders::class.java))))

          internal constructor(owner: MetaMyServiceClass) :
              super("headers",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaClientMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<HttpClient>> =
              register(MetaParameter(0,
              "p0",metaType<UnaryOperator<HttpClient>>(UnaryOperator::class.java,metaType<HttpClient>(HttpClient::class.java))))

          internal constructor(owner: MetaMyServiceClass) :
              super("client",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaUriMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          internal constructor(owner: MetaMyServiceClass) :
              super("uri",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaUri_1Method : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          private val p0Parameter: MetaParameter<UnaryOperator<String>> = register(MetaParameter(0,
              "p0",metaType<UnaryOperator<String>>(UnaryOperator::class.java,metaType<String>(String::class.java))))

          internal constructor(owner: MetaMyServiceClass) :
              super("uri",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaInputMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          private val p0Parameter: MetaParameter<TransportModuleConstants.DataFormat> =
              register(MetaParameter(0,
              "p0",metaEnum(TransportModuleConstants.DataFormat::class.java,
              TransportModuleConstants.DataFormat::valueOf)))

          internal constructor(owner: MetaMyServiceClass) :
              super("input",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaOutputMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          private val p0Parameter: MetaParameter<TransportModuleConstants.DataFormat> =
              register(MetaParameter(0,
              "p0",metaEnum(TransportModuleConstants.DataFormat::class.java,
              TransportModuleConstants.DataFormat::valueOf)))

          internal constructor(owner: MetaMyServiceClass) :
              super("output",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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

        public class MetaCookieMethod : StaticMetaMethod<MetaMyServiceClass, MyCommunicator> {
          private val p0Parameter: MetaParameter<String> = register(MetaParameter(0,
              "p0",metaType<String>(String::class.java)))

          private val p1Parameter: MetaParameter<Cookie> = register(MetaParameter(1,
              "p1",metaType<Cookie>(Cookie::class.java)))

          internal constructor(owner: MetaMyServiceClass) :
              super("cookie",metaType<MyCommunicator>(MyCommunicator::class.java),owner)

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
            register(MetaConstructorConstructor(this))

        private val valueField: MetaField<MetaModelClass, Sequence<String>> =
            register(MetaField("value",metaType<Sequence<String>>(Sequence::class.java,metaType<String>(String::class.java)),false,this))

        private final val getValueMethod: MetaGetValueMethod = register(MetaGetValueMethod(this))

        internal constructor() : super(metaType<Model>(Model::class.java))

        public fun `constructor`(): MetaConstructorConstructor = constructor

        public fun valueField(): MetaField<MetaModelClass, Sequence<String>> = valueField

        public fun getValueMethod(): MetaGetValueMethod = getValueMethod

        public companion object {
          private final val self: LazyProperty<MetaModelClass> = MetaClass.self(Model::class.java)

          public fun model(): MetaModelClass = self.get()
        }

        public class MetaConstructorConstructor : MetaConstructor<MetaModelClass, Model> {
          private val valueParameter: MetaParameter<Sequence<String>> = register(MetaParameter(0,
              "value",metaType<Sequence<String>>(Sequence::class.java,metaType<String>(String::class.java))))

          internal constructor(owner: MetaModelClass) :
              super(metaType<Model>(Model::class.java),owner)

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

        public class MetaGetValueMethod :
            InstanceMetaMethod<MetaModelClass, Model, Sequence<String>> {
          internal constructor(owner: MetaModelClass) :
              super("getValue",metaType<Sequence<String>>(Sequence::class.java,metaType<String>(String::class.java)),owner)

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
