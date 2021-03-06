package org.teiid.lsp.websocket;

import java.util.Collection;

import org.eclipse.lsp4j.jsonrpc.Launcher.Builder;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.websocket.WebSocketEndpoint;
import org.teiid.lsp.TeiidLanguageServer;

public class TeiidLSPWebSocketEndpoint extends WebSocketEndpoint<LanguageClient> {

	@Override
	protected void configure(Builder<LanguageClient> builder) {
		builder.setLocalService(new TeiidLanguageServer());
		builder.setRemoteInterface(LanguageClient.class);
	}

	@Override
	protected void connect(Collection<Object> localServices, LanguageClient remoteProxy) {
		localServices.stream()
			.filter(LanguageClientAware.class::isInstance)
			.forEach(languageClientAware -> ((LanguageClientAware) languageClientAware).connect(remoteProxy));
	}

}
