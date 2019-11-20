package ru.art.example

import ru.art.config.extensions.activator.AgileConfigurationsActivator.*
import ru.art.entity.PrimitiveMapping.*
import ru.art.http.constants.MimeToContentTypeMapper.*
import ru.art.http.server.HttpServer.*
import ru.art.http.server.function.HttpServiceFunction.*
import ru.art.http.server.module.HttpServerModule.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        useAgileConfigurations("example")

        httpGet("${httpServerModule().path}/test")
                .responseMapper(stringMapper.fromModel)
                .producesMimeType(textHtmlUtf8())
                .produce {
                    """<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Example</title>
  </head>
  <body>

    <h2>Add React in One Minute</h2>
    <p>This page demonstrates using React with no build tooling.</p>
    <p>React is loaded as a script tag.</p>

    <!-- We will put our React component inside this div. -->
    <div id="like_button_container"></div>

    <!-- Load React. -->
    <!-- Note: when deploying, replace "development.js" with "production.min.js". -->
    <script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
    <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>

    <!-- Load our React component. -->
    <script>
        'use strict';

const e = React.createElement;

class LikeButton extends React.Component {
  constructor(props) {
    super(props);
    this.state = { liked: false };
  }

  render() {
    if (this.state.liked) {
      return 'You liked this.';
    }

    return e(
      'button',
      { onClick: () => this.setState({ liked: true }) },
      'Like'
    );
  }
}

const domContainer = document.querySelector('#like_button_container');
ReactDOM.render(e(LikeButton), domContainer);
    </script>

  </body>
</html>"""
                }

        startHttpServer().await()
    }
}
